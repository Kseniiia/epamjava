package by.bsu.easytutor.dao;

import by.bsu.easytutor.entity.Course;
import by.bsu.easytutor.entity.Student;
import by.bsu.easytutor.entity.StudentCourse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseDAO extends AbstractDAO<Course> {
    public CourseDAO() {
        setClazz(Course.class);
    }

    public Optional<List> getByStudentId(long studentId) {
        StudentCourseDAO studentCourseDAO = new StudentCourseDAO();
        return studentCourseDAO.getByStudentId(studentId);
    }

//    public Optional<List> getByStudent(Student student) {
//
//    }
}
