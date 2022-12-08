package com.aiolos.graphqldemo.event;

import org.springframework.context.ApplicationEvent;

public class OtherEvent extends ApplicationEvent {
    private String message;

    public OtherEvent(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
