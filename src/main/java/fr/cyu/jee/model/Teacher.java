package fr.cyu.jee.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Target;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Teacher extends User {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
    private Set<Course> assignedCourses;

    protected Teacher() {
    }

    public Teacher(String email, String password, String firstName, String lastName, LocalDate dob){
        super(email, password, firstName, lastName, dob);
    }

    public Set<Course> getAssignedCourses() {
        return assignedCourses;
    }
}
