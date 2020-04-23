package by.bsu.easytutor.dao;

import by.bsu.easytutor.entity.Course;
import by.bsu.easytutor.entity.Student;
import by.bsu.easytutor.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAO {
    private Connection connection;

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM Users LEFT JOIN Students ON (id = user_id) WHERE id = ?";
    private static final String SQL_INSERT_USER = "INSERT INTO Users VALUES (NULL, ?, ?, ?, ?)";
    private static final String SQL_INSERT_STUDENT = "INSERT INTO Students VALUES (?, ?)";
    private static final String SQL_INSERT_STUDENT_COURSE = "INSERT INTO StudentsCourses VALUES (?, ?)";

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public Student get(long id) throws SQLException {
        Student student = null;

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            student = buildStudent(resultSet);
        }

        return student;
    }

    public void save(Student student) throws SQLException {
        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getLogin());
            preparedStatement.setString(2, student.getPassword());
            preparedStatement.setString(3, student.getName());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                student.setId(resultSet.getLong(1));
            }

            preparedStatement = connection.prepareStatement(SQL_INSERT_STUDENT);
            preparedStatement.setLong(1, student.getId());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    public void addToCourse(Student student, Course course) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_STUDENT_COURSE);
        preparedStatement.setLong(1, student.getId());
        preparedStatement.setLong(2, course.getId());
        preparedStatement.executeUpdate();
    }

    private Student buildStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();

        student.setId(resultSet.getLong("id"));
        student.setLogin(resultSet.getString("login"));
        student.setPassword(resultSet.getString("password"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setAge(resultSet.getInt("age"));

        return student;
    }
}