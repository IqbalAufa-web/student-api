package com.example.student_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course newCourse) {
        return courseService.addCourse(newCourse);
    }

    @GetMapping("/courses/search")
    public List<Course> searchCourses(@RequestParam String title) {
        return courseService.searchCoursesByTitle(title);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        try {
            courseService.removeCourse(id);
            return ResponseEntity.ok("Course deleted successfully");
        } catch (CannotDeleteCourseException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id, @Valid @RequestBody Course updatedCourse) {
        try {
            Course course = courseService.updateCourse(id, updatedCourse);
            return ResponseEntity.ok(course);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
