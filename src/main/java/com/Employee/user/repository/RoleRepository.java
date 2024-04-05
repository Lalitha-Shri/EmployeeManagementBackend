package com.Employee.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Employee.user.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
