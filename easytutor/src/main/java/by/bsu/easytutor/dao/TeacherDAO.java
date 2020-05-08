package by.bsu.easytutor.dao;

import by.bsu.easytutor.entity.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDAO extends AbstractDAO{
    public TeacherDAO() {
        setClazz(Teacher.class);
    }
}
