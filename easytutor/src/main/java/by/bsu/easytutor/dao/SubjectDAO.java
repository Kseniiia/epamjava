package by.bsu.easytutor.dao;

import by.bsu.easytutor.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SubjectDAO {
    private Connection connection;

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM Subjects WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO Subjects VALUES (NULL, ?)";
    private static final String SQL_DELETE = "DELETE FROM Subjects WHERE id = ?";

    public SubjectDAO(Connection connection) {
        this.connection = connection;
    }

    public Subject get(long id) throws SQLException {
        Subject subject = null;

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            subject = buildSubject(resultSet);
        }

        return subject;
    }

    public void save(Subject subject) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, subject.getName());
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if (resultSet.next()) {
            subject.setId(resultSet.getLong(1));
        }
    }

    public void delete(Subject subject) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
        preparedStatement.setLong(1, subject.getId());
        preparedStatement.executeUpdate();
    }


    private Subject buildSubject(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();

        subject.setId(resultSet.getLong("id"));
        subject.setName(resultSet.getString("name"));

        return subject;
    }
}