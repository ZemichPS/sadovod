package by.zemich.parserservice.endpoint.telegram.impl;

import by.zemich.parserservice.config.property.ViberProperty;
import by.zemich.parserservice.core.exeption.NotFoundProductCardException;
import by.zemich.parserservice.core.exeption.NotFoundSessionException;
import by.zemich.parserservice.core.model.ProductCardDto;
import by.zemich.parserservice.core.model.TelegramSessionDto;
import by.zemich.parserservice.core.model.telegram.ResponseTelegram;
import by.zemich.parserservice.core.model.telegram.ResponseTelegramAnswerCallBack;
import by.zemich.parserservice.core.model.viber.ViberPictureMessage;
import by.zemich.parserservice.endpoint.telegram.api.CallBackAnswer;
import by.zemich.parserservice.service.api.TelegramSessionFacade;
import by.zemich.parserservice.service.api.ViberService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class SendToViberCallBackAnswerImpl implements CallBackAnswer {

    private final TelegramSessionFacade sessionFacade;
    private final ViberProperty viberProperty;

    private final ViberService viberService;

    public SendToViberCallBackAnswerImpl(TelegramSessionFacade sessionFacade, ViberProperty viberProperty, ViberService viberService) {
        this.sessionFacade = sessionFacade;
        this.viberProperty = viberProperty;
        this.viberService = viberService;
    }

    @Override
    public ResponseTelegram apply(Update telegramUpdate) {

        String[] callBackArray = telegramUpdate.getCallbackQuery().getData().split("->");
        UUID productCardUuid = UUID.fromString(callBackArray[1]);

        String telegramUserId = telegramUpdate.getCallbackQuery().getFrom().getUserName();


        TelegramSessionDto currentUserSession = sessionFacade.getById(telegramUserId)
                .orElseThrow(() -> new NotFoundSessionException(telegramUserId));

        ProductCardDto productCardDto = getProductCardByUuid(productCardUuid, currentUserSession)
                .orElseThrow(() -> new NotFoundProductCardException(productCardUuid));

        productCardToListViberMessage(productCardDto).forEach(viberService::send);

        AnswerCallbackQuery answerCallbackQuery = AnswerCallbackQuery.builder()
                .callbackQueryId(telegramUpdate.getCallbackQuery().getId())
                .build();

        return new ResponseTelegramAnswerCallBack(answerCallbackQuery);
    }

    private Optional<ProductCardDto> getProductCardByUuid(UUID productCardUuid, TelegramSessionDto telegramSessionDto) {
        if (telegramSessionDto.getTelegramPostList() != null) {
            if (!telegramSessionDto.getTelegramPostList().isEmpty()) {
                return telegramSessionDto.getTelegramPostList().stream()
                        .filter(productCardDto -> productCardDto.getUuid().equals(productCardUuid))
                        .findFirst();
            }
        }

        return Optional.empty();
    }

    @Override
    public String getCallbackTrigger() {
        return "SEND_TO_VIBER";
    }

    private List<ViberPictureMessage> productCardToListViberMessage(ProductCardDto productCard) {
        AtomicInteger counter = new AtomicInteger(0);
        List<ViberPictureMessage> viberMessageList = productCard.getImageLinks().stream()
                .map(imageLink -> {
                    ViberPictureMessage pictureMessage = new ViberPictureMessage(
                            viberProperty.getToken(),
                            viberProperty.getId(),
                            imageLink
                    );

                    pictureMessage.setText("pic. " + counter.incrementAndGet());
                    return pictureMessage;

                }).toList();

        String postText = productMessageToText(productCard);
        viberMessageList.get(viberMessageList.size() - 1).setText(postText);

        return viberMessageList;
    }


    private String productMessageToText(ProductCardDto productCard){
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

        if (productCard.isNotInSize()) {
            message.append("Не в размер \n");
        } else if (productCard.isInSize()) {
            message.append("В размер \n");
        }

        if (productCard.isSale()) {
            message.append("\uD83C\uDF89 Распродажа!!! \n");
        }

        if (productCard.isOnlySetSale()) {
            message.append("Продажа только набором \n");
        }


        if (productCard.getCostPerSet() != null) {
            message.append(MessageFormat.format("Цена: {0} \n", productCard.getCostPerSet()));
        }


        if (productCard.getCostPerPeace() != null) {
            message.append(MessageFormat.format("Цена: {0} \n", productCard.getCostPerPeace()));
        }

        return message.toString();
    }
}
