package by.zemich.parserservice.core.model.viber;
import by.zemich.parserservice.core.enums.ViberMessageType;

import java.util.Objects;


public class ViberPictureMessage extends ViberTextMessage{

    private String media;
    private String thumbnail;

    public ViberPictureMessage(String authToken, String from, String text, String media, String thumbnail) {
        super(authToken, from, text);
        this.media = media;
        this.thumbnail = thumbnail;
        this.setType(ViberMessageType.PICTURE.toString().toLowerCase());
    }

    public ViberPictureMessage(String authToken, String from, String media) {
        super(authToken, from);
        this.media = media;
        this.setType(ViberMessageType.PICTURE.toString().toLowerCase());
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViberPictureMessage that = (ViberPictureMessage) o;
        return Objects.equals(media, that.media) && Objects.equals(thumbnail, that.thumbnail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(media, thumbnail);
    }
}
