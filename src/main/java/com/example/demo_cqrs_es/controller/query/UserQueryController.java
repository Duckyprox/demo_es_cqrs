package com.example.demo_cqrs_es.controller.query;

import com.example.demo_cqrs_es.dtos.UserDTO;
import com.example.demo_cqrs_es.dtos.UserRoleSnap;
import com.example.demo_cqrs_es.es.mediator.Mediator;
import com.example.demo_cqrs_es.es.mediator.QueryGateway;
import com.example.demo_cqrs_es.request.query.UserDetailQuery;
import com.example.demo_cqrs_es.request.query.UserFindAllQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/q")
@RestController
public class UserQueryController {
    private final QueryGateway queryGateway;

    public UserQueryController(Mediator mediator) {
        this.queryGateway = new QueryGateway(mediator);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> getUserById(@RequestParam Integer id) {
        UserRoleSnap snap = queryGateway.send(new UserDetailQuery(id));
        if (snap == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(snap, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAllUser() {
        List<UserDTO> users = queryGateway.send(new UserFindAllQuery());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
