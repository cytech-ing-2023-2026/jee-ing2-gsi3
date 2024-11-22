package fr.cyu.jee.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Target;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher extends User {

    @ManyToOne(optional = true)
    private Subject subject;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
    private Set<Course> assignedCourses;

    protected Teacher() {
    }

    public Teacher(String email, String password, String firstName, String lastName, LocalDate dob, Subject subject){
        super(email, password, firstName, lastName, dob);
        this.subject = subject;
        this.assignedCourses = new HashSet<>();
    }

    public Set<Course> getAssignedCourses() {
        return assignedCourses;
    }

    public Subject getSubject() {
        return subject;
    }
}
