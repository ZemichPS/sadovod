package by.zemich.parserservice.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "google")
public class GTranslateProperty {
    private String appllink;
    private String appid;

    public GTranslateProperty(String appllink, String appid) {
        this.appllink = appllink;
        this.appid = appid;
    }

    public GTranslateProperty() {
    }

    public String getAppllink() {
        return appllink;
    }

    public void setAppllink(String appllink) {
        this.appllink = appllink;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
