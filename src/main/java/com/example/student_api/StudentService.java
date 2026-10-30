package com.example.student_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CourseRepository courseRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student addStudent(Student newStudent) {
        return studentRepo.save(newStudent);
    }

    public Student enrollStudentInCourse(int studentId, int courseId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().add(course);

        return studentRepo.save(student);
    }

    public Student updateStudent(int id, Student updatedStudent) {
        Student existingStudent = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setAge(updatedStudent.getAge());

        return studentRepo.save(existingStudent);
    }

    public void removeStudent(int id) {
        // 1. Check if the student exists
        if (!studentRepo.existsById(id)) {
            // If they don't exist, throw an error so the Controller can catch it!
            throw new RuntimeException("Student Not Found");
        }

        // 2. If they do exist, delete them
        studentRepo.deleteById(id);
    }

    public List<Student> searchStudentsByName(String name) {
        return studentRepo.findByName(name);
    }

    public List<Student> searchStudentsByAge(int age) {
        return studentRepo.findByAge(age);
    }
        public org.springframework.data.domain.Page<Student> getStudentsPaginated(int pageNo, int pageSize) {
        // PageRequest asks for a specific page (0-indexed) and size
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(pageNo, pageSize);
        return studentRepo.findAll(pageable);
    }

}
