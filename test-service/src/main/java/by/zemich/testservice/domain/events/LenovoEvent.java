package by.zemich.testservice.domain.events;

import org.springframework.context.ApplicationEvent;

public class LenovoEvent extends ApplicationEvent {
    private String text;

    public LenovoEvent(Object source, String text) {
        super(source);
        this.text = text;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "LenovoEvent{" +
                "text='" + text + '\'' +
                '}';
    }
}
