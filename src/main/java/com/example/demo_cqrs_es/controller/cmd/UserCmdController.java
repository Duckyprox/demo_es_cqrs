package com.example.demo_cqrs_es.controller.cmd;

import com.example.demo_cqrs_es.es.mediator.CommandGateway;
import com.example.demo_cqrs_es.es.mediator.Mediator;
import com.example.demo_cqrs_es.request.cmd.UserAddRoleCmd;
import com.example.demo_cqrs_es.request.cmd.UserCreateCmd;
import com.example.demo_cqrs_es.request.cmd.UserDeleteCmd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/w/user")
public class UserCmdController {
    private final CommandGateway commandGateway;

    public UserCmdController(Mediator mediator) {
        this.commandGateway = new CommandGateway(mediator);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(UserCreateCmd cmd) {
        commandGateway.send(cmd);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam Integer id) {
        commandGateway.send(new UserDeleteCmd(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add-role")
    public ResponseEntity<?> addRoles(@RequestBody UserAddRoleCmd cmd) {
        commandGateway.send(cmd);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
