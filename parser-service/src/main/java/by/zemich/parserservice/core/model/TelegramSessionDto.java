package by.zemich.parserservice.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelegramSessionDto {
    private String userName;
    private Long chatId;
    private int pageCounter;
    private int postOffset = 0;
    private int postCount = 3;
    private UUID currentSupplierUuid;
    private List<ProductCardDto> telegramPostList = new ArrayList<>();

    public void addProductCar(ProductCardDto productCard){
        if(telegramPostList.size() >= 200){
            telegramPostList.remove(0);
        }

        telegramPostList.add(productCard);
    }

}
