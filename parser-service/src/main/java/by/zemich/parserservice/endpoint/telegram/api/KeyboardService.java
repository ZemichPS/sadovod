package by.zemich.parserservice.endpoint.telegram.api;

import by.zemich.parserservice.core.model.ButtonDataDto;
import by.zemich.parserservice.core.model.SupplierDto;
import org.telegram.telegrambots.meta.api.objects.menubutton.MenuButtonCommands;
import org.telegram.telegrambots.meta.api.objects.menubutton.MenuButtonDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public interface KeyboardService {

    ReplyKeyboardMarkup getReplyKeyboardMarkupForRegisteredUser();

    <T> List<InlineKeyboardButton> getInlineKeyboardButtonsFromList(List<T> objectList);
    List<InlineKeyboardButton> getInlineSupplierKeyboardButtonsFromList(List<SupplierDto> supplierDtoList);

    InlineKeyboardMarkup getInlineKeyboardMarkupForTwoOption(ButtonDataDto opt1, ButtonDataDto opt2);

    InlineKeyboardButton getInlineKeyBoardButton(String text, String callbackData);




}
