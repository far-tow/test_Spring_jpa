package se.fartow.test_spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.fartow.test_spring.exceptions.DataNotFoundException;
import se.fartow.test_spring.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Student persist(Student student) {
        if (student == null) throw new IllegalArgumentException("Student was null");
        entityManager.persist(student);
        return student;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findById(String id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    @Override
    @Transactional(readOnly = false)
    public Optional<Student> findByEmail(String email) {
        //email.toLowerCase(); to convert the email to LowerCase. But better to use is in the query like "UPPER(s.email)
        return entityManager.createQuery("SELECT s from Student s WHERE UPPER(s.email) = UPPER(:e)", Student.class)
                .setParameter("e", email)
                .getResultStream()
                .findFirst();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Student> findByNameContains(String name) {
        return entityManager.createQuery("SELECT s FROM Student s " +
                        "WHERE " +
                        "UPPER(s.firstName) LIKE UPPER(CCONCAT('%', :name, '%'))" +
                        " OR " +
                        "UPPER(s.lastName) LIKE UPPER(CONCAT('%', :name, '%'))", Student.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Student> findAll() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();

    }

    @Override
    @Transactional
    public Student update(Student student) {
        return entityManager.merge(student);
    }

    @Override
    @Transactional
    public void remove(String id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null) entityManager.remove(student);
        else try {
            throw new DataNotFoundException("Student id dosen't exist");
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
