package com.example.demo_cqrs_es.dtos;

import com.example.demo_cqrs_es.entities.Role;
import com.example.demo_cqrs_es.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserRoleSnap implements Serializable {
    private Integer userId;
    private User user;
    private List<Role> roles;
}
