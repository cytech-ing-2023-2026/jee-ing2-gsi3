package com.example.gradesystem.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String lastName;
    private String firstName;
    private List<Grade> grades;

    public Student(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.grades = new ArrayList<>();
    }

    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public List<Grade> getGrades() { return grades; }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public void displayGrades() {
        System.out.println("Grades of " + firstName + " " + lastName + ":");
        for (Grade grade : grades) {
            System.out.println(grade.getSubject() + ": " + grade.getValue());
        }
    }
}
