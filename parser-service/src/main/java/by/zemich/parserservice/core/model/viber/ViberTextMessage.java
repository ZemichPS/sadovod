package by.zemich.parserservice.core.model.viber;

import by.zemich.parserservice.core.enums.ViberMessageType;

public class ViberTextMessage extends ViberMessage{

    private String text;

    public ViberTextMessage(String authToken, String from) {
        super(authToken, from);
        setType(ViberMessageType.TEXT.toString().toLowerCase());
    }

    public ViberTextMessage(String authToken, String from, String text) {
        super(authToken, from);
        setType(ViberMessageType.TEXT.toString().toLowerCase());
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
