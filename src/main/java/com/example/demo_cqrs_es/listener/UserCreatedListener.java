package com.example.demo_cqrs_es.listener;

import com.example.demo_cqrs_es.config.RabbitConfig;
import com.example.demo_cqrs_es.dtos.UserRoleSnap;
import com.example.demo_cqrs_es.entities.User;
import com.example.demo_cqrs_es.events.UserCreatedEvent;
import com.example.demo_cqrs_es.services.RedisService;
import com.example.demo_cqrs_es.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserCreatedListener {

    private final ObjectMapper objectMapper;
    private final RedisService redisService;
    private final UserService userService;

    public UserCreatedListener(ObjectMapper objectMapper, RedisService redisService, UserService userService) {
        this.objectMapper = objectMapper;
        this.redisService = redisService;
        this.userService = userService;
    }

    @RabbitListener(queues = RabbitConfig.USER_CREATED)
    public void onMessageReceived(UserCreatedEvent event) throws JsonProcessingException {
        User addedUser = userService.findUserById(event.getUserId());
        UserRoleSnap snap = new UserRoleSnap();
        snap.setUserId(addedUser.getId());
        snap.setUser(addedUser);
        snap.setRoles(new ArrayList<>());
        redisService.save(addedUser.getId().toString(), objectMapper.writeValueAsString(snap));
    }
}
