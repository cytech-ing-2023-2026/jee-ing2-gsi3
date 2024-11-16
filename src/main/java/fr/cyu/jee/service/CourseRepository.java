package fr.cyu.jee.service;

import fr.cyu.jee.model.Course;
import fr.cyu.jee.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {

    @Query(value = "SELECT * FROM courses c INNER JOIN students_courses sc ON c.id = sc.course_id WHERE sc.student_id = :studentId AND c.begin_date BETWEEN :from AND :to", nativeQuery = true)
    Set<Course> getStudentCourses(@Param("studentId") int studentId, @Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query(value = "SELECT * FROM courses c WHERE c.teacher_id = :teacherId AND c.begin_date BETWEEN :from AND :to", nativeQuery = true)
    Set<Course> getTeacherCourses(@Param("teacherId") int teacherId, @Param("from") LocalDate from, @Param("to") LocalDate to);
}

