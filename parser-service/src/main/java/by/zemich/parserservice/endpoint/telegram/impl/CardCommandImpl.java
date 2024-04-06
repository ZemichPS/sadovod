package by.zemich.parserservice.endpoint.telegram.impl;

import by.zemich.parserservice.core.enums.ECallbackType;
import by.zemich.parserservice.core.enums.EActionType;
import by.zemich.parserservice.core.exeption.NotFoundSupplierException;
import by.zemich.parserservice.core.model.*;
import by.zemich.parserservice.core.model.telegram.*;
import by.zemich.parserservice.endpoint.telegram.api.KeyboardService;
import by.zemich.parserservice.service.api.*;
import by.zemich.parserservice.endpoint.telegram.api.Command;
import by.zemich.parserservice.service.api.ProductCardMaker;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CardCommandImpl implements Command {

    private final VKService vkService;
    private final UserFacade userFacade;
    private final ProductCardMaker<VkPostDto> productCardMaker;
    private final KeyboardService keyboardService;
    private final TelegramSessionFacade sessionFacade;


    public CardCommandImpl(VKService vkService,
                           UserFacade userFacade,
                           ProductCardMaker<VkPostDto> productCardMaker,
                           KeyboardService keyboardService,
                           TelegramSessionFacade sessionFacade) {
        this.vkService = vkService;
        this.userFacade = userFacade;
        this.productCardMaker = productCardMaker;
        this.keyboardService = keyboardService;
        this.sessionFacade = sessionFacade;
    }


    @Override
    public String getCommand() {
        return "/product_cards";
    }

    @Override
    public EActionType getCommandType() {
        return EActionType.GET_CARD;
    }

    @Override
    public ResponseTelegram apply(Update telegramUpdate) {

        Long chatId;
        String telegramUserId;

        if (telegramUpdate.getMessage() != null) {
            chatId = telegramUpdate.getMessage().getChatId();
            telegramUserId = telegramUpdate.getMessage().getFrom().getUserName();
        } else {
            chatId = telegramUpdate.getCallbackQuery().getMessage().getChatId();
            telegramUserId = telegramUpdate.getCallbackQuery().getFrom().getUserName();
        }


        if (sessionFacade.getById(telegramUserId).isEmpty()) {
            SendMessage sendMessage = SendMessage.builder()
                    .chatId(chatId)
                    .text("Поставщик товаров не выбран. Чтобы получить карточки товаров выберите поставщика")
                    .build();
            return new ResponseTelegramJustMessage(sendMessage);
        }

        TelegramSessionDto currentUserSession = sessionFacade.getById(telegramUserId).get();
        UserDto userDto = userFacade.getByUsername(telegramUserId);

        UUID supplierUuid = currentUserSession.getCurrentSupplierUuid();
        SupplierDto supplier = getSupplierByUuidForUser(supplierUuid, userDto);

        List<ProductCardTelegramDto> productCardTelegramDtoList = getVkPosts(supplier, currentUserSession).stream()
                .map(productCardMaker::create)
                .map(productCard -> productCurdToResponseTelegramCard(productCard, chatId))
                .map(this::addKeyboardToPost)
                .toList();

        SendMessage nextPostsMessage = getNextPostsMessage(currentUserSession, chatId);


        if(productCardTelegramDtoList.isEmpty()){
            SendMessage sendMessage = SendMessage.builder()
                    .chatId(chatId)
                    .text("Не удалось построить посты. Возможно это связано с тем что нет VK постов удовлетворяющих вашему запросу. Попробуйте сменить поставщика")
                    .build();

            if(telegramUpdate.getCallbackQuery() != null){
                AnswerCallbackQuery answerCallbackQuery = AnswerCallbackQuery.builder()
                        .callbackQueryId(telegramUpdate.getCallbackQuery().getId())
                        .build();
                return new ResponseTelegramTextAndCallback(sendMessage, answerCallbackQuery);
            }

            return new ResponseTelegramJustMessage(sendMessage);
        }


        return new ResponseTelegramProductPostList(chatId, productCardTelegramDtoList, nextPostsMessage, telegramUserId);
    }

    private List<VkPostDto> getVkPosts(SupplierDto supplier, TelegramSessionDto session) {

        int currentOffset = session.getPostOffset();
        int postCount = session.getPostCount();
        int offset = session.getPostOffset();


        VKPostQuery vkPostQuery = VKPostQuery.builder()
                .supplier(supplier)
                .offset(offset)
                .interval(7)
                .count(postCount)
                .offset(currentOffset)
                .build();

        return vkService.getPosts(vkPostQuery);
    }

    private SupplierDto getSupplierByUuidForUser(UUID supplierUuid, UserDto userDto) {
        return userDto.getSuppliers().stream()
                .filter(supplierDto -> supplierDto.getUuid().equals(supplierUuid))
                .findFirst().orElseThrow(() -> new NotFoundSupplierException(supplierUuid.toString()));

    }

    private ProductCardTelegramDto addKeyboardToPost(ProductCardTelegramDto productCardTelegramDto) {
        String callBackDataOpt1 = ECallbackType.SEND_TO_VIBER + "->" + productCardTelegramDto.getProductCard().getUuid();
        String callBackDataOpt2 = ECallbackType.SET_PRICE + "->" + productCardTelegramDto.getProductCard().getUuid();

        ButtonDataDto opt1 = ButtonDataDto.builder()
                .buttonText("Отправить в Viber")
                .callBackData(callBackDataOpt1)
                .build();

        ButtonDataDto opt2 = ButtonDataDto.builder()
                .buttonText("Редактировать стоимость")
                .callBackData(callBackDataOpt2)
                .build();

        InlineKeyboardMarkup keyboardMarkup = keyboardService.getInlineKeyboardMarkupForTwoOption(opt1, opt2);
        productCardTelegramDto.getSendMessage().setReplyMarkup(keyboardMarkup);
        return productCardTelegramDto;
    }

    private ProductCardTelegramDto productCurdToResponseTelegramCard(ProductCardDto productCard, Long chatId) {

        StringBuilder message = new StringBuilder();

        if (!productCard.getProductCategory().equals("")) {
            message.append(MessageFormat.format("{0} \n", productCard.getProductCategory().toUpperCase()));
        }
        if (!productCard.getSize().equals("")) {
            message.append(MessageFormat.format("Размеры: {0} \n", productCard.getSize()));
        }
        if (!productCard.getFabric().equals("")) {
            message.append(MessageFormat.format("Ткань: {0} \n", productCard.getFabric()));
        }

        if (!productCard.getColor().equals("")) {
            message.append(MessageFormat.format("Цвета: {0} \n", productCard.getColor()));
        }

        if (!productCard.getSeason().equals("")) {
            message.append(MessageFormat.format("Сезон: {0} \n", productCard.getSeason()));
        }

        if (productCard.isFleece()) {
            message.append("На флисе \n");
        }

        if (productCard.isInSize()) {
            message.append("В размер \n");
        } else if (productCard.isNotInSize()) {
            message.append("Не в размер \n");
        }

        if (productCard.isSale()) {
            message.append("Распродажа!!! \n");
        }

        if (productCard.isOnlySetSale()) {
            message.append("Продажа только набором \n");
        }


        if (productCard.getCostPerSet() != null) {
            message.append(MessageFormat.format("Стоимость за набор: {0} \n", productCard.getCostPerSet()));
        }


        if (productCard.getCostPerPeace() != null) {
            message.append(MessageFormat.format("Стоимость за штуку: {0} \n", productCard.getCostPerPeace()));
        }

        message.append("🖇️ \n");

        message.append(MessageFormat.format("UUID карточки: {0} \n", productCard.getUuid()));
        message.append(MessageFormat.format("Опубликован: {0} \n", productCard.getPublishedAt()));
        message.append(MessageFormat.format("Ссылка: {0} \n", productCard.getLink()));


        SendMessage sendMessage = SendMessage.builder()
                .text(message.toString())
                .chatId(chatId)
                .build();

        ProductCardTelegramDto responseTelegramCard = new ProductCardTelegramDto(sendMessage);
        responseTelegramCard.setProductCard(productCard);
        responseTelegramCard.setSendPhotoList(new ArrayList<>());

        if (CollectionUtils.isNotEmpty(productCard.getImageLinks())) {

            List<SendPhoto> sendPhotoList = productCard.getImageLinks().stream()
                    .map(InputFile::new)
                    .map(file -> SendPhoto.builder()
                            .photo(file)
                            .chatId(chatId)
                            .caption(productCard.getUuid().toString())
                            .build())
                    .toList();

            responseTelegramCard.setSendPhotoList(sendPhotoList);
        }

        return responseTelegramCard;

    }


    private SendMessage getNextPostsMessage(TelegramSessionDto session, Long chatId) {
        Integer pageCount = session.getPostCount();

        InlineKeyboardButton button = InlineKeyboardButton.builder()
                .text("🔜")
                .callbackData("NEXT_POSTS")
                .build();

        InlineKeyboardMarkup inlineKeyboard = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(button))
                .build();


        return SendMessage.builder()
                .text("Получить следующие %S постов".formatted(pageCount))
                .replyMarkup(inlineKeyboard)
                .chatId(chatId)
                .build();
    }


}
