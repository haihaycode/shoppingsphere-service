package com.shoppingsphere.shoppingsphereservice.repository;

import com.shoppingsphere.shoppingsphereservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
