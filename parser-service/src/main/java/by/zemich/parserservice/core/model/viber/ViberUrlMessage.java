package by.zemich.parserservice.core.model.viber;


import by.zemich.parserservice.core.enums.ViberMessageType;

import java.util.Objects;

public class ViberUrlMessage extends ViberTextMessage{

    private String media;

    public ViberUrlMessage(String authToken, String from) {
        super(authToken, from);
        this.setType(ViberMessageType.URL.toString().toLowerCase());
    }

    public ViberUrlMessage(String authToken, String from, String text, String media) {
        super(authToken, from, text);
        this.media = media;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViberUrlMessage that = (ViberUrlMessage) o;
        return Objects.equals(media, that.media);
    }

    @Override
    public int hashCode() {
        return Objects.hash(media);
    }
}
