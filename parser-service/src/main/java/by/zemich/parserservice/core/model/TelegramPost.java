package by.zemich.parserservice.core.model;

import by.zemich.parserservice.service.api.Post;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class TelegramPost extends by.zemich.parserservice.core.model.Post implements Post<TelegramContentDto> {
    public TelegramPost(ProductCardDto productCard) {
        super(productCard);
    }

    @Override
    public TelegramContentDto create() {

        StringBuilder message = new StringBuilder();

        if (!getProductCard().getProductCategory().equals("")) {
            message.append(MessageFormat.format("{0} \n", getProductCard().getProductCategory().toUpperCase()));
        }
        if (!getProductCard().getSize().equals("")) {
            message.append(MessageFormat.format("Размеры: {0} \n", getProductCard().getSize()));
        }
        if (!getProductCard().getFabric().equals("")) {
            message.append(MessageFormat.format("Ткань: {0} \n", getProductCard().getFabric()));
        }

        if (!getProductCard().getColor().equals("")) {
            message.append(MessageFormat.format("Цвета: {0} \n", getProductCard().getColor()));
        }

        if (!getProductCard().getSeason().equals("")) {
            message.append(MessageFormat.format("Сезон: {0} \n", getProductCard().getSeason()));
        }

        if (getProductCard().isFleece()) {
            message.append("На флисе \n");
        }

        if (getProductCard().isNotInSize()) {
            message.append("Не в размер \n");
        }

        if (getProductCard().isInSize()) {
            message.append("В размер \n");
        }

        if (getProductCard().isSale()) {
            message.append("Распродажа!!! \n");
        }

        if (getProductCard().isOnlySetSale()) {
            message.append("Продажа только набором \n");
        }


        if (getProductCard().getCostPerSet() != null) {
            message.append(MessageFormat.format("Стоимость за набор: {0} \n", getProductCard().getCostPerSet()));
        }


        if (getProductCard().getCostPerPeace() != null) {
            message.append(MessageFormat.format("Стоимость за штуку: {0} \n", getProductCard().getCostPerPeace()));
        }

        message.append(MessageFormat.format("Опубликован: {0} \n", getProductCard().getPublishedAt()));
        message.append(MessageFormat.format("Ссылка: {0} \n", getProductCard().getLink()));


        SendMessage sendMessage = SendMessage.builder()
                .text(message.toString())
                .build();

        TelegramContentDto telegramPost = TelegramContentDto.builder()
                .productCard(getProductCard())
                .message(sendMessage)
                .sendPhotos(new ArrayList<>())
                .build();

        if (CollectionUtils.isNotEmpty(getProductCard().getImageLinks())) {

            List<SendPhoto> sendPhotoList = getProductCard().getImageLinks().stream()
                    .map(InputFile::new)
                    .map(file -> SendPhoto.builder()
                            .photo(file)
                            .build())
                    .toList();

            telegramPost.setSendPhotos(sendPhotoList);
        }

        return telegramPost;

    }
}
