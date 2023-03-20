package com.taemin.blogsearch.core.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class BlogQueryEvent extends ApplicationEvent {

    private String query;

    public BlogQueryEvent(Object source, String query) {
        super(source);
        this.query = query;
    }
}
