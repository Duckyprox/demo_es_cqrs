package com.example.demo_cqrs_es.handler.cmd;

import com.example.demo_cqrs_es.entities.User;
import com.example.demo_cqrs_es.es.mediator.RequestHandler;
import com.example.demo_cqrs_es.es.util.UserUtil;
import com.example.demo_cqrs_es.events.UserCreatedEvent;
import com.example.demo_cqrs_es.request.cmd.UserCreateCmd;
import com.example.demo_cqrs_es.services.NotifyService;
import com.example.demo_cqrs_es.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserCreateHandler implements RequestHandler<UserCreateCmd, Void> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateHandler.class);

    private final UserService userService;
    private final NotifyService notifyService;

    public UserCreateHandler(UserService userService, NotifyService notifyService) {
        this.userService = userService;
        this.notifyService = notifyService;
    }

    @Override
    public Void handle(UserCreateCmd request) {
        int id = UserUtil.getUsers().size() + 1;
        userService.addNewUser((new User(id, "User_" + id)));
        UserCreatedEvent createdEvent = UserCreatedEvent.builder().userId(id).userName("User_" + id).build();
        notifyService.notifyAddedNewUser(createdEvent);
        LOGGER.info("create user successful. user_id = {}", id);
        return null;
    }
}
