package by.bsu.easytutor.dao;

import by.bsu.easytutor.entity.Subject;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectDAO extends AbstractDAO{
    public SubjectDAO() {
        setClazz(Subject.class);
    }
}