package com.example.student_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // That's it. Spring Boot writes all the CRUD SQL automatically!
    List<Student> findByName(String name);

    List<Student> findByAge(int Age);

}
