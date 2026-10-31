package com.example.student_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        if (!studentRepo.existsById(id)) {
            throw new RuntimeException("Student Not Found");
        }
        studentRepo.deleteById(id);
    }

    public List<Student> searchStudentsByName(String name) {
        return studentRepo.findByName(name);
    }

    public List<Student> searchStudentsByAge(int age) {
        return studentRepo.findByAge(age);
    }

    public Page<Student> getStudentsPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return studentRepo.findAll(pageable);
    }
}
