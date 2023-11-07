package com.example.demo_cqrs_es.request.cmd;


import com.example.demo_cqrs_es.es.mediator.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddRoleCmd implements Request<Void> {
    private Integer userId;
    private String roleCode;
}
