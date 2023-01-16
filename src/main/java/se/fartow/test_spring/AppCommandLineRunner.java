package se.fartow.test_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.fartow.test_spring.dao.StudentDao;
import se.fartow.test_spring.entity.Student;

import java.time.LocalDate;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    @Autowired
    StudentDao studentDao;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("#####################");
        Student createdStudent = studentDao.persist(
                new Student(
                "Test",
                "Test",
                "test@test.se",
                LocalDate.parse("2000-01-01"))
        );
        System.out.println(createdStudent);
        System.out.println("#####################");
    }
}
