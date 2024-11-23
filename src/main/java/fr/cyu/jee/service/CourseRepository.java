package fr.cyu.jee.service;

import fr.cyu.jee.model.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(value = "SELECT * FROM courses c INNER JOIN students_courses sc ON c.id = sc.course_id WHERE sc.student_id = :studentId AND c.begin_date BETWEEN :from AND :to", nativeQuery = true)
    Set<Course> getStudentCourses(@Param("studentId") int studentId, @Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query(value = "SELECT * FROM courses c WHERE c.teacher_id = :teacherId AND c.begin_date BETWEEN :from AND :to", nativeQuery = true)
    Set<Course> getTeacherCourses(@Param("teacherId") int teacherId, @Param("from") LocalDate from, @Param("to") LocalDate to);
}