package com.example.demo_cqrs_es.services;

import com.example.demo_cqrs_es.entities.User;
import com.example.demo_cqrs_es.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addNewUser(User user) {
        return this.userRepository.save(user);
    }

    public void deleteUserById(Integer id) {
        this.userRepository.deleteById(id);
    }

    public User findUserById(Integer id) {
        return this.userRepository.findById(id).orElseThrow();
    }

    public User findUserByName(String name) {
        return this.userRepository.findByName(name).orElseThrow();
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
