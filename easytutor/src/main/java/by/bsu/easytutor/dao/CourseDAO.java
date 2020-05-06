package by.bsu.easytutor.dao;

import by.bsu.easytutor.entity.Course;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDAO extends AbstractDAO<Course> {
    public CourseDAO() {
        setClazz(Course.class);
    }
}