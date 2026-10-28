package com.example.student_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course addCourse(Course newCourse) {
        return courseRepo.save(newCourse);
    }

    public List<Course> searchCoursesByTitle(String title) {
        return courseRepo.findByTitle(title);
    }

    public void removeCourse(int id) {
        // 1. Find the course. If it doesn't exist, throw an error.
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // 2. Check if the course has students. 
        if (!course.getStudents().isEmpty()) {
            // Throw our custom exception!
            throw new CannotDeleteCourseException("Cannot delete course because students are enrolled in it.");
        }

        // 3. If it's safe, delete it.
        courseRepo.deleteById(id);

    }

    public Course updateCourse(int id, Course updatedCourse) {
        Course existingCourse = courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course Not Found"));

        existingCourse.setTitle(updatedCourse.getTitle());

        return courseRepo.save(existingCourse);
    }
}
