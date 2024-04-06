package by.zemich.parserservice.core.model;

import lombok.Builder;
import lombok.Data;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import java.util.List;

@Data
@Builder
public class TelegramContentMessageDto {
    private SendMessage sendMessages;
    private SendPhoto sendPhotos;
    private List<TelegramContentDto> telegramPosts;
}
