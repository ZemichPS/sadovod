package by.zemich.parserservice.endpoint.telegram.impl;

import by.zemich.parserservice.core.enums.EActionType;
import by.zemich.parserservice.core.enums.EUserStatus;
import by.zemich.parserservice.core.model.ERole;
import by.zemich.parserservice.core.model.UserDto;
import by.zemich.parserservice.core.model.telegram.ResponseTelegram;
import by.zemich.parserservice.core.model.telegram.ResponseTelegramJustMessage;
import by.zemich.parserservice.endpoint.telegram.api.Command;
import by.zemich.parserservice.endpoint.telegram.api.KeyboardService;
import by.zemich.parserservice.service.api.TelegramSessionFacade;
import by.zemich.parserservice.service.api.UserFacade;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class RegisterCommandImpl implements Command {

    private final TelegramSessionFacade sessionFacade;
    private final UserFacade userFacade;
    private final KeyboardService keyboardService;

    public RegisterCommandImpl(TelegramSessionFacade sessionFacade, UserFacade userFacade, KeyboardService keyboardService) {
        this.sessionFacade = sessionFacade;
        this.userFacade = userFacade;
        this.keyboardService = keyboardService;
    }


    @Override
    public String getCommand() {
        return "/sign_up";
    }

    @Override
    public EActionType getCommandType() {
        return EActionType.REGISTRATION;
    }

    @Override
    public ResponseTelegram apply(Update telegramUpdate) {


        Long chatId = telegramUpdate.getMessage().getChatId();
        User telegramUser = telegramUpdate.getMessage().getFrom();
        Long telegramUserId = telegramUpdate.getMessage().getFrom().getId();

        if (userFacade.existsByTelegramUserId(telegramUserId)) {
            SendMessage message = SendMessage.builder()
                    .text(String.format("Учётная запись c telegram user id %s уже существует. В повторной регистрации нет необходимости!", telegramUserId))
                    .chatId(chatId)
                    .build();
            return new ResponseTelegramJustMessage(message);
        }

        UserDto userDto = UserDto.builder()
                .uuid(UUID.randomUUID())
                .username(telegramUser.getUserName())
                .firstname(telegramUser.getFirstName())
                .telegramId(telegramUser.getId())
                .role(ERole.ADMIN)
                .status(EUserStatus.ACTIVATED)
                .lastname(telegramUser.getLastName())
                .chatID(chatId)
                .build();

        userFacade.create(userDto);
        sessionFacade.create(telegramUpdate);

        SendMessage message = SendMessage.builder()
                .text("Поздравляем! Вы успешно зарегистрированы!")
                .chatId(chatId)
                .build();

        return new ResponseTelegramJustMessage(message);
    }
}
