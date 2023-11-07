package com.example.demo_cqrs_es.request.query;

import com.example.demo_cqrs_es.dtos.UserDTO;
import com.example.demo_cqrs_es.es.mediator.Request;

import java.util.List;

public class UserFindAllQuery implements Request<List<UserDTO>> {
}
