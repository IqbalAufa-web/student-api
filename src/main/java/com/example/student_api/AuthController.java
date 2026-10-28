package com.example.student_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // 1. Find the user in the database
        User user = userRepository.findByUsername(username);

        // 2. Check if user exists and password matches
        if (user != null && user.getPassword().equals(password)) {
            // 3. Generate the VIP Pass (Token)
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            // 4. Access Denied
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
