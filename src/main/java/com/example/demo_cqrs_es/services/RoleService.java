package com.example.demo_cqrs_es.services;

import com.example.demo_cqrs_es.entities.Role;
import com.example.demo_cqrs_es.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
