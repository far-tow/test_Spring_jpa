package se.fartow.test_spring.dao;

import se.fartow.test_spring.entity.Student;

import java.util.Collection;
import java.util.Optional;

public interface StudentDao {
    Student persist (Student student);
    Optional<Student> findById(String id);
    Optional<Student> findByEmail(String email);
    Collection<Student> findByNameContains(String name);
    Collection<Student> findAll();
    Student update (Student student);
    void remove (String id);
}
