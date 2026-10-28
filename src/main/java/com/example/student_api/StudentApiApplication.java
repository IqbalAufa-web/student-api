package com.example.student_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);
    }

    // This runs automatically when the server starts
    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            // Create an admin user if it doesn't exist
            if (userRepository.findByUsername("admin") == null) {
                userRepository.save(new User("admin", "password"));
                System.out.println("✅ Admin user created! Username: admin, Password: password");
            }
        };
    }
}
