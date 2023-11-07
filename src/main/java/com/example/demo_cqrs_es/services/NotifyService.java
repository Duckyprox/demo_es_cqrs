package com.example.demo_cqrs_es.services;

import com.example.demo_cqrs_es.config.RabbitConfig;
import com.example.demo_cqrs_es.events.UserCreatedEvent;
import com.example.demo_cqrs_es.events.UserDeletedEvent;
import com.example.demo_cqrs_es.events.UserAddedRoleEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotifyService {

    RabbitTemplate rabbitTemplate;

    public NotifyService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void notifyAddedNewUser(UserCreatedEvent event) {
        rabbitTemplate.convertAndSend(RabbitConfig.CQRS_DIRECT_EXCHANGE, RabbitConfig.USER_CREATED, event);
    }

    public void notifyDeletedUser(UserDeletedEvent event) {
        rabbitTemplate.convertAndSend(RabbitConfig.CQRS_DIRECT_EXCHANGE, RabbitConfig.USER_DELETED, event);
    }

    public void notifyUserUpdatedRole(UserAddedRoleEvent event) {
        rabbitTemplate.convertAndSend(RabbitConfig.CQRS_DIRECT_EXCHANGE, RabbitConfig.USER_ADDED_ROLE, event);
    }
}
