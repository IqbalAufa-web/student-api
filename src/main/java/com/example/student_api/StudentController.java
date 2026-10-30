package com.example.student_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
public class StudentController {

    // We only inject the Service now, not the repositories!
    @Autowired
    private StudentService studentService;

    // 1. GET all students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // 2. POST a new student
    @PostMapping("/students")
    public Student addStudent(@Valid @RequestBody Student newStudent) {
        return studentService.addStudent(newStudent);
    }

    // 3. PUT (Update)
    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable int id, @Valid @RequestBody Student updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }

    // 4. DELETE
    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        try {
            studentService.removeStudent(id);
            return ResponseEntity.ok("Student deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 5. ENROLL
    @PostMapping("/students/{studentId}/courses/{courseId}")
    public Student enrollStudentInCourse(@PathVariable int studentId, @PathVariable int courseId) {
        return studentService.enrollStudentInCourse(studentId, courseId);
    }

    @GetMapping("/students/search")
    public List<Student> searchStudents(@RequestParam String name) {
        return studentService.searchStudentsByName(name);
    }

    @GetMapping("/students/search/age")
    public List<Student> searchStudents(@RequestParam int age) {
        return studentService.searchStudentsByAge(age);
    }

    @Autowired
    private StudentProfileService profileService;

    @PostMapping("/students/{id}/profile")
    public Student addProfile(@PathVariable int id, @RequestBody StudentProfile profile) {
        return profileService.addProfileToStudent(id, profile);
    }

    @GetMapping("/students/paginated")
    public org.springframework.data.domain.Page<Student> getStudentsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return studentService.getStudentsPaginated(page, size);
    }
}
