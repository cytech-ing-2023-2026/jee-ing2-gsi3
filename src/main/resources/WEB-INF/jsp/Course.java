package com.example.gestionscolarite.model;

public class Course {
    private String code;
    private String title;
    private Enseignant teacher;

    public Course(String code, String title, Enseignant teacher) {
        this.code = code;
        this.title = title;
        this.teacher = teacher;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public Enseignant getTeacher() {
        return teacher;
    }
}
