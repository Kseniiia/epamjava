package by.bsu.easytutor.service;

import by.bsu.easytutor.dao.CourseDAO;
import by.bsu.easytutor.dao.DAOFactory;
import by.bsu.easytutor.dao.StudentDAO;
import by.bsu.easytutor.entity.Course;
import by.bsu.easytutor.entity.Student;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StudentService {
    private final Logger logger = LogManager.getLogger();

    private DAOFactory daoFactory;

    public StudentService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void addToCourse(long studentId, long courseId) throws Exception, SQLException {
        logger.info("Add student to course");

        StudentDAO studentDAO = daoFactory.getStudentDAO();
        CourseDAO courseDAO = daoFactory.getCourseDAO();

        Student student = studentDAO.get(studentId);
        Course course = courseDAO.get(courseId);

        if (student == null) throw new Exception("Student doesn't exist.");
        if (course == null) throw new Exception("Course doesn't exist.");

        studentDAO.addToCourse(student, course);
    }

    public List<Course> getCourses(long studentId) throws Exception {
        logger.info("Add course");
        CourseDAO courseDAO = daoFactory.getCourseDAO();
        StudentDAO studentDAO = daoFactory.getStudentDAO();

        Student student = studentDAO.get(studentId);

        if (student == null) throw new Exception("Student doesn't exist.");

        return courseDAO.getByStudentId(studentId);
    }
}