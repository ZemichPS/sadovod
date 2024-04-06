package by.zemich.parserservice.endpoint.telegram.impl;

import by.zemich.parserservice.core.model.ButtonDataDto;
import by.zemich.parserservice.core.model.SupplierDto;
import by.zemich.parserservice.endpoint.telegram.api.KeyboardService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.menubutton.MenuButtonCommands;
import org.telegram.telegrambots.meta.api.objects.menubutton.MenuButtonDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyboardServiceImpl implements KeyboardService {

    @Override
    public ReplyKeyboardMarkup getReplyKeyboardMarkupForRegisteredUser() {
        KeyboardButton goodsCardButton = KeyboardButton.builder()
                .text("Карточки товаров")
                .build();

        KeyboardButton supplierButton = KeyboardButton.builder()
                .text("Список поставщиков")
                .build();

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(goodsCardButton);


        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(supplierButton);

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(keyboardRow1, keyboardRow2))
                .build();
    }

    @Override
    public <T> List<InlineKeyboardButton> getInlineKeyboardButtonsFromList(List<T> objectList) {
        return null;
    }

    @Override
    public List<InlineKeyboardButton> getInlineSupplierKeyboardButtonsFromList(List<SupplierDto> supplierDtoList) {


        return supplierDtoList.stream()
                .map(supplier ->
                        InlineKeyboardButton.builder()
                                .text(supplier.getName())
                                .url(supplier.getVkLink())
                                .callbackData(supplier.getUuid().toString())
                                .build()

                ).toList();
    }

    @Override
    public InlineKeyboardMarkup getInlineKeyboardMarkupForTwoOption(ButtonDataDto opt1, ButtonDataDto opt2) {
        InlineKeyboardButton button1 = InlineKeyboardButton.builder().text(opt1.getButtonText()).callbackData(opt1.getCallBackData()).build();
        InlineKeyboardButton button2 = InlineKeyboardButton.builder().text(opt2.getButtonText()).callbackData(opt2.getCallBackData()).build();

        List<InlineKeyboardButton> line1 = new ArrayList<>();
        line1.add(button1);
        line1.add(button2);

        return InlineKeyboardMarkup.builder()
                .keyboard(List.of(line1))
                .build();
    }

    @Override
    public InlineKeyboardButton getInlineKeyBoardButton(String text, String callbackData) {
        return InlineKeyboardButton.builder()
                .text(text)
                .callbackData(callbackData)
                .build();
    }

}
