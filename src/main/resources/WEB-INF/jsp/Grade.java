package com.example.gradesystem.model;

public class Grade {
    private String subject;
    private double value;
    private String teacher;

    public Grade(String subject, double value, String teacher) {
        this.subject = subject;
        this.value = value;
        this.teacher = teacher;
    }

    public String getSubject() { return subject; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public String getTeacher() { return teacher; }
}
