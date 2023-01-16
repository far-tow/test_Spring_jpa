package se.fartow.test_spring.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.fartow.test_spring.entity.Student;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class StudentDaoImplTest {
    @Autowired
    TestEntityManager em;

    @Autowired
    StudentDaoImpl testObject;

    String createdStudentId1;
    String createdStudentId2;
    @BeforeEach
    public void setup(){
        Student studentData1 = new Student("Erik", "Eriksson", "erik@test.se", LocalDate.parse("2000-01-01"));
        Student studentData2 = new Student("Erika", "Svensson", "erika@test.se", LocalDate.parse("2000-02-01"));

        Student createdStudent1 = em.persist(studentData1);
        Student createdStudent2 = em.persist(studentData2);

        createdStudentId1 = createdStudent1.getId();
        createdStudentId2 = createdStudent2.getId();
    }

    @Test
    public void persist(){
        Student studentData = new Student("Peter", "Nisson", "peter@test.se", LocalDate.parse("2001-01-01"));
        Student createdStudent = testObject.persist(studentData);

        assertNotNull(createdStudent);
        assertNotNull(createdStudent.getId());
    }

    // TODO: 2023-01-16 Add more tests

}
