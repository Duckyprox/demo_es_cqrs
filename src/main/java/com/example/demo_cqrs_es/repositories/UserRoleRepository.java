package com.example.demo_cqrs_es.repositories;

import com.example.demo_cqrs_es.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
//    List<UserRole> findAllByUserAndRole(Integer userId, Integer roleId);
}
