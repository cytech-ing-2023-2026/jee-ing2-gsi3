package fr.cyu.jee.model;

import java.time.LocalDate;

public enum UserType {
    ADMIN, TEACHER, STUDENT;

    public User createUser(String email, String password, String firstName, String lastName, LocalDate dob, Subject subject) {
        return switch (this) {
            case ADMIN -> new Admin(email, password, firstName, lastName, dob);
            case TEACHER -> new Teacher(email, password, firstName, lastName, dob, subject);
            case STUDENT -> new Student(email, password, firstName, lastName, dob);
        };
    }

    public User createUser(String email, String password, String firstName, String lastName, LocalDate dob) {
        return createUser(email, password, firstName, lastName, dob, null);
    }
}
