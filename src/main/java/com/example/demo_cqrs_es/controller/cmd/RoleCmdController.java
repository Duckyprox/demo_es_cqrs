package com.example.demo_cqrs_es.controller.cmd;

import com.example.demo_cqrs_es.es.mediator.CommandGateway;
import com.example.demo_cqrs_es.es.mediator.Mediator;
import com.example.demo_cqrs_es.request.cmd.UserCreateCmd;
import com.example.demo_cqrs_es.request.cmd.UserDeleteCmd;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/w/role")
public class RoleCmdController {
    private final CommandGateway commandGateway;

    public RoleCmdController(Mediator mediator) {
        this.commandGateway = new CommandGateway(mediator);
    }
}
