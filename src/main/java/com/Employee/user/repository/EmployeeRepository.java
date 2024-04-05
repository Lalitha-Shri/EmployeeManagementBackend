package com.Employee.user.repository;

import com.Employee.user.entity.User1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<User1,Long> {
   Optional<User1> findByEmail(String email);

}
