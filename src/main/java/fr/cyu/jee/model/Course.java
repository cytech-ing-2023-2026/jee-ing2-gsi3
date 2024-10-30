package fr.cyu.jee.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDateTime beginDate;

    @Column(nullable = false)
    private Duration duration;

    @ManyToOne(optional = false)
    private Subject subject;

    @ManyToOne(optional = false)
    private Teacher teacher;

    protected Course() {
    }

    public Course(LocalDateTime beginDate, Duration duration, Subject subject, Teacher teacher) {
        this.beginDate = beginDate;
        this.duration = duration;
        this.subject = subject;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    //Used by Hibernate
    private void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
