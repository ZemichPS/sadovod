package by.zemich.parserservice.core.model.viber;


import java.io.Serializable;

public abstract class ViberMessage implements Serializable {
    private String authToken;
    private String from;
    private String type;

    public ViberMessage(String authToken, String from) {
        this.authToken = authToken;
        this.from = from;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
