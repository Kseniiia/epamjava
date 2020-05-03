package by.bsu.easytutor.dao;

import by.bsu.easytutor.entity.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class SubjectDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT = "INSERT INTO Subjects VALUES (NULL, ?)";
    private static final String SQL_DELETE = "DELETE FROM Subjects WHERE id = ?";

    public Subject get(long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.get(Subject.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public boolean save(Subject subject) {
        return jdbcTemplate.update(SQL_INSERT, subject.getName()) > 0;
    }

    public boolean delete(Subject subject) {
        return jdbcTemplate.update(SQL_DELETE, subject.getId()) > 0;
    }
}