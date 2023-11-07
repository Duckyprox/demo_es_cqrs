package com.example.demo_cqrs_es.mapper;

import com.example.demo_cqrs_es.dtos.UserDTO;
import com.example.demo_cqrs_es.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);

    List<UserDTO> toDtos(List<User> users);
}
