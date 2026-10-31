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
        Student student = studentService.getAllStudents().stream()
                .filter(s -> s.getId() == studentId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Student not found"));

        profile.setStudent(student);
        student.setProfile(profile);

        return studentService.addStudent(student);
    }
}
