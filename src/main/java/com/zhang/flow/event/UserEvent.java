package com.zhang.flow.event;

import org.springframework.context.ApplicationEvent;

public class UserEvent<T> extends ApplicationEvent {
    private final T data;
    public UserEvent(T source) {
        super(source);
        this.data = source;
    }

    public T getData(){
        return data;
    }

}
