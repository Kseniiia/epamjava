package by.bsu.easytutor.dao;

import by.bsu.easytutor.entity.Student;
import by.bsu.easytutor.entity.StudentCourse;

import java.util.List;
import java.util.Optional;

public class StudentCourseDAO extends AbstractDAO{
    public StudentCourseDAO() {
        setClazz(StudentCourse.class);
    }

    public Optional<List> getByStudentId(long studentId) {
        return Optional.of(getCurrentSession().createQuery("from StudentCourse where student.id = :studentId")
                .setParameter("studentId", studentId).list());
    }

    public Optional<List> getByCourseId(long courseId) {
        return Optional.of(getCurrentSession().createQuery("from StudentCourse where course.id = :courseId")
                .setParameter("courseId", courseId).list());
    }

//    public Optional<List> getByStudent(Student student) {
//        return
//    }
}
