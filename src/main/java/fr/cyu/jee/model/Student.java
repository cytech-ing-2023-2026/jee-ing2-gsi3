package fr.cyu.jee.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student extends User {

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Grade> grades;

    @ManyToMany
    @JoinTable(name="students_courses",
            joinColumns=@JoinColumn(name="student_id"),
            inverseJoinColumns=@JoinColumn(name="course_id")
    )
    private Set<Course> courses;

    protected Student() {}

    public Student(String email, String password, String firstName, String lastName, LocalDate dob, Set<Grade> grades, Set<Course> courses){
        super(email, password, firstName, lastName, dob);
        this.grades = grades;
        this.courses = courses;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    public void addGrade(Subject subject, double value) {
        grades.add(new Grade(subject, this, value));
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
