package by.bsu.easytutor.service;

import by.bsu.easytutor.dao.CourseDAO;
import by.bsu.easytutor.dao.StudentDAO;
import by.bsu.easytutor.entity.Course;
import by.bsu.easytutor.entity.Student;

import java.util.List;
import java.util.NoSuchElementException;

import by.bsu.easytutor.dto.StudentDTO;
import by.bsu.easytutor.mapper.StudentMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentService {

    //@Autowired
    private StudentDAO studentDAO;

    private StudentMapper mapper;

    @Autowired
    StudentService(StudentDAO studentDAO, StudentMapper mapper) {
        this.studentDAO = studentDAO;
        this.mapper = mapper;
    }

    @Autowired
    private CourseDAO courseDAO;

    private final Logger logger = LogManager.getLogger();

    public void addToCourse(long studentId, long courseId) throws Throwable {
        logger.info("Add student to course");

        Student student = (Student) studentDAO.get(studentId).orElseThrow(() -> new NoSuchElementException("No student with id: " + studentId));
        Course course = courseDAO.get(courseId).orElseThrow(() -> new NoSuchElementException("No course with id: " + courseId));

        studentDAO.addToCourse(student, course);
    }

    public List<Course> getCourses(long studentId) {
        logger.info("Add course");

        //Student student = studentDAO.get(studentId).orElseThrow(() -> new NoSuchElementException("No student with id: " + studentId));

        return courseDAO.getByStudentId(studentId).orElseThrow(() -> new NoSuchElementException("No student with id: " + studentId));
    }

    public void createStudent(Student student) {
        logger.info("Create student");
        studentDAO.save(student);
    }

    public void createStudent(StudentDTO studentDTO) {
        logger.info("Create student");
        studentDAO.save(mapper.toEntity(studentDTO));
    }
}