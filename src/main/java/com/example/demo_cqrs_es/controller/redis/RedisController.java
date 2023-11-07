package com.example.demo_cqrs_es.controller.redis;

import com.example.demo_cqrs_es.entities.User;
import com.example.demo_cqrs_es.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/r/user")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping
    public void addStudent(@RequestBody User user) {
        redisService.save(user.getId().toString(), user.getName());
    }

    @GetMapping
    public Map<String, String> getAllUsers() {
        return redisService.findAll();
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable String id) {
        return redisService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id, @RequestBody User user) {
//        String existingUser = redisService.findById(id);
//        if (existingUser != null) {
//            user.setId(Integer.valueOf(id));
//            redisService.update(user);
//        }
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        redisService.delete(id);
    }
}
