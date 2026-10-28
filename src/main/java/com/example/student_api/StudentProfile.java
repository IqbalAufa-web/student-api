package com.example.student_api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String phoneNumber;
    private String address;

    // The One-to-One relationship
    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @JsonIgnore // Prevents infinite loop when printing Student
    private Student student;

    public StudentProfile() {
    }

    public StudentProfile(String phoneNumber, String address) {
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
