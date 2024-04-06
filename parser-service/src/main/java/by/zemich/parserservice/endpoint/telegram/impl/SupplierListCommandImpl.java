package by.zemich.parserservice.endpoint.telegram.impl;

import by.zemich.parserservice.core.enums.EActionType;
import by.zemich.parserservice.core.model.telegram.ResponseTelegram;
import by.zemich.parserservice.core.model.telegram.ResponseTelegramJustMessage;
import by.zemich.parserservice.endpoint.telegram.api.Command;
import by.zemich.parserservice.service.api.UserFacade;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Component
public class SupplierListCommandImpl implements Command {

    private final UserFacade userFacade;


    public SupplierListCommandImpl(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Override
    public String getCommand() {
        return "/supplier_list";
    }

    @Override
    public EActionType getCommandType() {
        return EActionType.GET_SUPPLIER_LIST;
    }

    @Override
    public ResponseTelegram apply(Update telegramUpdate) {

        Long telegramUserId = telegramUpdate.getMessage().getFrom().getId();


       List<List<InlineKeyboardButton>> keyboardLists = userFacade.getByTelegramId(telegramUserId).getSuppliers().stream()
                .map(supplierDto -> {
                    String supplierUuid = supplierDto.getUuid().toString();
                    String buttonText = supplierDto.getAddress() + " " + supplierDto.getSupplierType().getTitle();
                    String callbackData = "supplier_uuid" + "->" + supplierUuid;
                    InlineKeyboardButton keyboardButton = InlineKeyboardButton.builder()
                            .text(buttonText)
                            .callbackData(callbackData)
                            .build();
                    return List.of(keyboardButton);
                }).toList();

        InlineKeyboardMarkup supplierInlineKeyboardMarkup = InlineKeyboardMarkup.builder()
                .keyboard(keyboardLists)
                .build();



        SendMessage sendMessage = SendMessage.builder()
                .text("Список ваших поставщиков.")
                .chatId(telegramUpdate.getMessage().getChatId())
                .replyMarkup(supplierInlineKeyboardMarkup)
                .build();

        return new ResponseTelegramJustMessage(sendMessage);
    }
}
