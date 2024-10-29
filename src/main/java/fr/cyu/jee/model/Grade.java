package fr.cyu.jee.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Student student;

    @Column(nullable = false)
    private double value;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime date;

    protected Grade() {}

    public Grade(Subject subject, Student student, double value) {
        this.subject = subject;
        this.student = student;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    //Used by Hibernate
    private void setId(int id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
