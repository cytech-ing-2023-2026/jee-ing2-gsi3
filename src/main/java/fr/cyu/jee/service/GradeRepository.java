package fr.cyu.jee.service;

import fr.cyu.jee.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    @Query(
            value = """
                    SELECT grades.* FROM grades
                        INNER JOIN users ON grades.student_id = users.id
                        INNER JOIN subjects ON grades.subject_id = subjects.id
                        WHERE grades.subject_id = :subjectId
                        ORDER BY
                            users.last_name ASC,
                            users.first_name ASC,
                            subjects.name ASC
                    """,
            nativeQuery = true
    )
    List<Grade> getAllBySubjectOrdered(@Param("subjectId") int subjectId);

    @Query(
            value = """
                    SELECT grades.* FROM grades
                        INNER JOIN users ON grades.student_id = users.id
                        INNER JOIN subjects ON grades.subject_id = subjects.id
                        ORDER BY
                            users.last_name ASC,
                            users.first_name ASC,
                            subjects.name ASC
                    """,
            nativeQuery = true
    )
    List<Grade> getAllOrdered();

    @Query(
            value = """
                    SELECT grades.* FROM grades
                        INNER JOIN users ON grades.student_id = users.id
                        INNER JOIN subjects ON grades.subject_id = subjects.id
                        WHERE grades.student_id = :studentId
                        ORDER BY
                            users.last_name ASC,
                            users.first_name ASC,
                            subjects.name ASC
                    """,
            nativeQuery = true
    )
    List<Grade> getAllByStudentOrdered(@Param("studentId") int studentId);
}