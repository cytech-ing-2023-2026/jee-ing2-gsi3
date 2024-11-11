package com.example.gradesystem.model;

public class Teacher {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void addGrade(Student student, String subject, double value) {
        student.addGrade(new Grade(subject, value, this.name));
    }

    public void modifyGrade(Student student, String subject, double newValue) {
        for (Grade grade : student.getGrades()) {
            if (grade.getSubject().equals(subject) && grade.getTeacher().equals(this.name)) {
                grade.setValue(newValue);
                System.out.println("Grade modified for " + student.getFirstName() + " " + student.getLastName() + " in " + subject + ": " + newValue);
                return;
            }
        }
        System.out.println("Modification not possible: this grade was not assigned by " + name);
    }
}
