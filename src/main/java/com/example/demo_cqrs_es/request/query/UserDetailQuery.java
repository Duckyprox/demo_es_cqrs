package com.example.demo_cqrs_es.request.query;

import com.example.demo_cqrs_es.dtos.UserDTO;
import com.example.demo_cqrs_es.dtos.UserRoleSnap;
import com.example.demo_cqrs_es.es.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailQuery implements Request<UserRoleSnap> {
    private Integer id;
}
