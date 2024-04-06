package by.zemich.parserservice.core.model;

import lombok.Builder;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Update;
@Data
@Builder
public class UpdateDto {
    Update telegramUpdate;
    TelegramSessionDto telegramSession;

}
