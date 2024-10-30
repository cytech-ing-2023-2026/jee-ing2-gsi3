package fr.cyu.jee.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student extends User {

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Grade> grades = new HashSet<>();

    protected Student() {}

    public Student(String email, String password, String firstName, String lastName, LocalDate dob){
        super(email, password, firstName, lastName, dob);
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
}
