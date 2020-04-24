package by.bsu.easytutor.service;

import by.bsu.easytutor.dao.CourseDAO;
import by.bsu.easytutor.dao.StudentDAO;
import by.bsu.easytutor.entity.Course;
import by.bsu.easytutor.entity.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StudentService {
    private final Logger logger = LogManager.getLogger();

    public void addToCourse(long studentId, long courseId) throws Exception, SQLException {
        logger.info("Add student to course");

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();

        Student student = studentDAO.get(studentId).orElseThrow(() -> new NoSuchElementException("No student with id: " + studentId));
        Course course = courseDAO.get(courseId).orElseThrow(() -> new NoSuchElementException("No course with id: " + courseId));

        studentDAO.addToCourse(student, course);
    }

    public List<Course> getCourses(long studentId) throws Exception {
        logger.info("Add course");

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();

        Student student = studentDAO.get(studentId).orElseThrow(() -> new NoSuchElementException("No student with id: " + studentId));

        return courseDAO.getByStudentId(studentId);
    }
}