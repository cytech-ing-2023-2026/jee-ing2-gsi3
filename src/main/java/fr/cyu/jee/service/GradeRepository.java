package fr.cyu.jee.service;

import fr.cyu.jee.model.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Integer> {
}
