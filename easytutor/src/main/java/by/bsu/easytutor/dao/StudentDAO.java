package by.bsu.easytutor.dao;

import by.bsu.easytutor.entity.Course;
import by.bsu.easytutor.entity.Student;
import by.bsu.easytutor.entity.StudentCourse;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO extends AbstractDAO<Student>{
    public StudentDAO() {
        setClazz(Student.class);
    }

    public void addToCourse(Student student, Course course) {
        StudentCourseDAO studentCourseDAO = new StudentCourseDAO();
        studentCourseDAO.save(new StudentCourse(student, course));
    }
}