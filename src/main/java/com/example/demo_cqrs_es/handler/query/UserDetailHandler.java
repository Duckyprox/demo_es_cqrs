package com.example.demo_cqrs_es.handler.query;

import com.example.demo_cqrs_es.dtos.UserDTO;
import com.example.demo_cqrs_es.dtos.UserRoleSnap;
import com.example.demo_cqrs_es.entities.User;
import com.example.demo_cqrs_es.es.mediator.RequestHandler;
import com.example.demo_cqrs_es.mapper.UserMapper;
import com.example.demo_cqrs_es.request.query.UserDetailQuery;
import com.example.demo_cqrs_es.services.RedisService;
import com.example.demo_cqrs_es.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDetailHandler implements RequestHandler<UserDetailQuery, UserRoleSnap> {

    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final RedisService redisService;

    public UserDetailHandler(ObjectMapper objectMapper, UserService userService, RedisService redisService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.redisService = redisService;
    }

    @Override
    public UserRoleSnap handle(UserDetailQuery request) {
        User user = userService.findUserById(request.getId());
        String snapJson = redisService.findById(user.getId().toString());
        UserRoleSnap userRedis;
        try {
            userRedis = objectMapper.readValue(snapJson, UserRoleSnap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return userRedis;
    }
}
