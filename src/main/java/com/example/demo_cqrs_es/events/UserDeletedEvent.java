package com.example.demo_cqrs_es.events;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
@Builder
public class UserDeletedEvent extends ApplicationEvent {
    Integer userId;

    public UserDeletedEvent(Integer userId) {
        super(userId);
        this.userId = userId;
    }
}
