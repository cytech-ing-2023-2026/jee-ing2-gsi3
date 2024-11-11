package com.example.gestionscolarite.model;

public class Inscription {
    private Etudiant student;
    private Course course;

    public Inscription(Etudiant student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Etudiant getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }
}
