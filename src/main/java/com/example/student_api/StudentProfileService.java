package com.example.student_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentProfileService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentProfileRepository profileRepo;

    public Student addProfileToStudent(int studentId, StudentProfile profile) {
        // 1. Find the student
        Student student = studentService.getAllStudents().stream()
                .filter(s -> s.getId() == studentId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // 2. Link the profile to the student
        profile.setStudent(student);
        student.setProfile(profile);

        // 3. Save the student (because of CascadeType.ALL, it will save the profile too!)
        return studentService.addStudent(student);
    }
}
