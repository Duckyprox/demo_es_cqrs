package com.example.demo_cqrs_es.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RedisService {

    private static final String HASH_KEY = "UserRole";

    private RedisTemplate<Object, Object> redisTemplate;
    private HashOperations<Object, String, String> hashOperations;

    @Autowired
    public RedisService(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void save(String key, String value) {
        hashOperations.put(HASH_KEY, key, value);
    }

    public Map<String, String> findAll() {
        return hashOperations.entries(HASH_KEY);
    }

    public String findById(String id) {
        return hashOperations.get(HASH_KEY, id);
    }

    public void update(String key, String value) {
        save(key, value);
    }

    public void delete(String id) {
        hashOperations.delete(HASH_KEY, id);
    }
}
