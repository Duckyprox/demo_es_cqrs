package com.example.demo_cqrs_es.handler.cmd;

import com.example.demo_cqrs_es.es.mediator.RequestHandler;
import com.example.demo_cqrs_es.events.UserDeletedEvent;
import com.example.demo_cqrs_es.request.cmd.UserDeleteCmd;
import com.example.demo_cqrs_es.services.NotifyService;
import com.example.demo_cqrs_es.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserDeleteHandler implements RequestHandler<UserDeleteCmd, Void> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDeleteHandler.class);

    private final UserService userService;
    private final NotifyService notifyService;

    public UserDeleteHandler(UserService userService, NotifyService notifyService) {
        this.userService = userService;
        this.notifyService = notifyService;
    }

    @Override
    public Void handle(UserDeleteCmd request) {
        userService.deleteUserById(request.getId());
        UserDeletedEvent event = UserDeletedEvent.builder().userId(request.getId()).build();
        notifyService.notifyDeletedUser(event);
        LOGGER.info("delete user successful. user_id = {}", request.getId());

        return null;
    }
}
