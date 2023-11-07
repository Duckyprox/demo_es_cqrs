package com.example.demo_cqrs_es.events;

import com.example.demo_cqrs_es.entities.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.List;


@Getter
@Setter
@Builder
public class UserAddedRoleEvent extends ApplicationEvent {
    Integer userId;
    Role role;

    public UserAddedRoleEvent(Integer userId, Role role) {
        super(userId);
        this.userId = userId;
        this.role = role;
    }
}
