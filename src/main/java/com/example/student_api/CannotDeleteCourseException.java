package com.example.student_api;

public class CannotDeleteCourseException extends RuntimeException {

    public CannotDeleteCourseException(String message) {
        super(message);
    }
}
