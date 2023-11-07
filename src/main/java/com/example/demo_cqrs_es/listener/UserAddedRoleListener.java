package com.example.demo_cqrs_es.listener;

import com.example.demo_cqrs_es.config.RabbitConfig;
import com.example.demo_cqrs_es.dtos.UserRoleSnap;
import com.example.demo_cqrs_es.entities.Role;
import com.example.demo_cqrs_es.entities.User;
import com.example.demo_cqrs_es.events.UserAddedRoleEvent;
import com.example.demo_cqrs_es.events.UserCreatedEvent;
import com.example.demo_cqrs_es.services.RedisService;
import com.example.demo_cqrs_es.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAddedRoleListener {

    private final ObjectMapper objectMapper;
    private final RedisService redisService;
    private final UserService userService;

    public UserAddedRoleListener(ObjectMapper objectMapper, RedisService redisService, UserService userService) {
        this.objectMapper = objectMapper;
        this.redisService = redisService;
        this.userService = userService;
    }

    @RabbitListener(queues = RabbitConfig.USER_ADDED_ROLE)
    public void onMessageReceived(UserAddedRoleEvent event) throws JsonProcessingException {
        User userAddedRole = userService.findUserById(event.getUserId());
        if (userAddedRole != null) {
            String dataRedis = redisService.findById(event.getUserId().toString());
            UserRoleSnap snap = objectMapper.readValue(dataRedis, UserRoleSnap.class);
            if (snap != null) {
                List<Role> roles = snap.getRoles();
                roles.add(event.getRole());
                snap.setRoles(roles);
                redisService.update(event.getUserId().toString(), objectMapper.writeValueAsString(snap));
            }
        }
    }
}
