package com.example.demo_cqrs_es.handler.query;

import com.example.demo_cqrs_es.dtos.UserDTO;
import com.example.demo_cqrs_es.es.mediator.RequestHandler;
import com.example.demo_cqrs_es.mapper.UserMapper;
import com.example.demo_cqrs_es.request.query.UserFindAllQuery;
import com.example.demo_cqrs_es.services.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFindAllHandler implements RequestHandler<UserFindAllQuery, List<UserDTO>> {

    private final UserService userService;

    public UserFindAllHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserDTO> handle(UserFindAllQuery request) {
        return UserMapper.INSTANCE.toDtos(userService.findAll());
    }
}
