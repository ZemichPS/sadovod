package by.zemich.parserservice.core.model.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public class ResponseTelegramProductPostList extends ResponseTelegram {

    private Long chatId;
    private List<ProductCardTelegramDto> productCardTelegramDtoList;
    private SendMessage nextPostsMessage;

    private String telegramUserId;

    public ResponseTelegramProductPostList(Long chatId, List<ProductCardTelegramDto> productCardTelegramDtoList, SendMessage nextPostsMessage, String telegramUserId) {
        this.chatId = chatId;
        this.productCardTelegramDtoList = productCardTelegramDtoList;
        this.nextPostsMessage = nextPostsMessage;
        this.telegramUserId = telegramUserId;
    }

    public ResponseTelegramProductPostList() {
    }

    public List<ProductCardTelegramDto> getProductCardTelegramDtoList() {
        return productCardTelegramDtoList;
    }

    public void setProductCardTelegramDtoList(List<ProductCardTelegramDto> productCardTelegramDtoList) {
        this.productCardTelegramDtoList = productCardTelegramDtoList;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public SendMessage getNextPostsMessage() {
        return nextPostsMessage;
    }

    public void setNextPostsMessage(SendMessage nextPostsMessage) {
        this.nextPostsMessage = nextPostsMessage;
    }

    public String getTelegramUserId() {
        return telegramUserId;
    }

    public void setTelegramUserId(String telegramUserId) {
        this.telegramUserId = telegramUserId;
    }
}
