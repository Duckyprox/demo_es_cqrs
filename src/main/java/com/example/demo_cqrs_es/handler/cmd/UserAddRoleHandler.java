package com.example.demo_cqrs_es.handler.cmd;

import com.example.demo_cqrs_es.entities.Role;
import com.example.demo_cqrs_es.entities.User;
import com.example.demo_cqrs_es.entities.UserRole;
import com.example.demo_cqrs_es.es.mediator.RequestHandler;
import com.example.demo_cqrs_es.events.UserAddedRoleEvent;
import com.example.demo_cqrs_es.request.cmd.UserAddRoleCmd;
import com.example.demo_cqrs_es.services.NotifyService;
import com.example.demo_cqrs_es.services.RoleService;
import com.example.demo_cqrs_es.services.UserRoleService;
import com.example.demo_cqrs_es.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserAddRoleHandler implements RequestHandler<UserAddRoleCmd, Void> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAddRoleHandler.class);

    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;
    private final NotifyService notifyService;

    public UserAddRoleHandler(UserService userService, RoleService roleService, UserRoleService userRoleService, NotifyService notifyService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
        this.notifyService = notifyService;
    }

    @Override
    public Void handle(UserAddRoleCmd request) {
        User user = userService.findUserById(request.getUserId());
        Role newRole = new Role();
        newRole.setCode(request.getRoleCode());
        roleService.save(newRole);
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(newRole);
        userRoleService.save(userRole);

        UserAddedRoleEvent updatedRoleEvent = UserAddedRoleEvent.builder().userId(request.getUserId())
                                                      .role(newRole).build();
        notifyService.notifyUserUpdatedRole(updatedRoleEvent);
        LOGGER.info("user added role successful. user_id = {}", request.getUserId());
        return null;
    }
}
