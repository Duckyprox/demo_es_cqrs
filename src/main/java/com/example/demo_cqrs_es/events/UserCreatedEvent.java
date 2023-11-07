package com.example.demo_cqrs_es.events;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
@Builder
public class UserCreatedEvent extends ApplicationEvent {
    Integer userId;
    String userName;

    public UserCreatedEvent(Integer userId, String userName) {
        super(userId);
        this.userId = userId;
        this.userName = userName;
    }
}
