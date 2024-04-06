package by.zemich.parserservice.dao.entity;

import by.zemich.parserservice.core.model.ProductCardDto;
import by.zemich.parserservice.core.model.SupplierDto;
import by.zemich.parserservice.core.model.TelegramContentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("session")
public class TelegramSessionHash {
    @Id
    private String userName;
    private Long chatId;
    private int pageCounter;
    private int postOffset;
    private int postCount;
    private UUID currentSupplierUuid;
    private List<ProductCardDto> telegramPostList = new ArrayList<>();


}
