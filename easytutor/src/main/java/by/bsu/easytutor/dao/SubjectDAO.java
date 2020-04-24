package by.bsu.easytutor.dao;

import by.bsu.easytutor.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class SubjectDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM Subjects WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO Subjects VALUES (NULL, ?)";
    private static final String SQL_DELETE = "DELETE FROM Subjects WHERE id = ?";

    public Optional<Subject> get(long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, (rs, rowNumber) -> Optional.of(buildSubject(rs)));
    }

    public boolean save(Subject subject) {
        return jdbcTemplate.update(SQL_INSERT, subject.getName()) > 0;
    }

    public boolean delete(Subject subject) {
        return jdbcTemplate.update(SQL_DELETE, subject.getId()) > 0;
    }


    private Subject buildSubject(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();

        subject.setId(resultSet.getLong("id"));
        subject.setName(resultSet.getString("name"));

        return subject;
    }
}