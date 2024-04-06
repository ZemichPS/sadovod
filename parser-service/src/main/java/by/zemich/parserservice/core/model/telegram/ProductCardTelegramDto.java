package by.zemich.parserservice.core.model.telegram;

import by.zemich.parserservice.core.model.ProductCardDto;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import java.util.List;

public class ProductCardTelegramDto {
    private ProductCardDto productCard;
    private List<SendPhoto> sendPhotoList;
    private SendMessage sendMessage;

    public ProductCardTelegramDto(ProductCardDto productCard, List<SendPhoto> sendPhotoList, SendMessage sendMessage) {
        this.productCard = productCard;
        this.sendPhotoList = sendPhotoList;
        this.sendMessage = sendMessage;
    }

    public ProductCardTelegramDto(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    public ProductCardTelegramDto() {
    }

    public ProductCardDto getProductCard() {
        return productCard;
    }

    public void setProductCard(ProductCardDto productCard) {
        this.productCard = productCard;
    }

    public List<SendPhoto> getSendPhotoList() {
        return sendPhotoList;
    }

    public void setSendPhotoList(List<SendPhoto> sendPhotoList) {
        this.sendPhotoList = sendPhotoList;
    }

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }
}
