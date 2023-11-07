package com.example.demo_cqrs_es.listener;

import com.example.demo_cqrs_es.config.RabbitConfig;
import com.example.demo_cqrs_es.entities.User;
import com.example.demo_cqrs_es.events.UserCreatedEvent;
import com.example.demo_cqrs_es.services.RedisService;
import com.example.demo_cqrs_es.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserDeletedListener {

    private final ObjectMapper objectMapper;
    private final RedisService redisService;
    private final UserService userService;

    public UserDeletedListener(ObjectMapper objectMapper, RedisService redisService, UserService userService) {
        this.objectMapper = objectMapper;
        this.redisService = redisService;
        this.userService = userService;
    }

    @RabbitListener(queues = RabbitConfig.USER_DELETED)
    public void onMessageReceived(UserCreatedEvent event) {
        User deletedUser = userService.findUserById(event.getUserId());
        if (deletedUser != null) {
            redisService.delete(deletedUser.getId().toString());
        }
    }
}
