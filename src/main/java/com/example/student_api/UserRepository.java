package com.example.student_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Spring Boot writes the SQL for this automatically
    User findByUsername(String username);
}
