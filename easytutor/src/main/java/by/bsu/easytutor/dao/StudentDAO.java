package by.bsu.easytutor.dao;

import by.bsu.easytutor.entity.Course;
import by.bsu.easytutor.entity.Student;
import by.bsu.easytutor.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM Users LEFT JOIN Students ON (id = user_id) WHERE id = ?";
    private static final String SQL_INSERT_USER = "INSERT INTO Users VALUES (NULL, ?, ?, ?, ?)";
    private static final String SQL_INSERT_STUDENT = "INSERT INTO Students VALUES ((SELECT id FROM USERS WHERE login = ?), ?)";
    private static final String SQL_INSERT_STUDENT_COURSE = "INSERT INTO StudentsCourses VALUES (?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE USERS SET login = ?, password = ?, name = ?, email = ? WHERE id = ?";
    private static final String SQL_UPDATE_STUDENT = "UPDATE STUDENTS SET age = ? WHERE user_id = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM Users WHERE id = ?";


    public Optional<Student> get(long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, (rs, rowNumber) -> Optional.of(buildStudent(rs)));
    }

    public boolean save(Student student) {
        return jdbcTemplate.update(SQL_INSERT_USER, student.getLogin(), student.getPassword(), student.getName(), student.getEmail()) > 0
        && jdbcTemplate.update(SQL_INSERT_STUDENT, student.getLogin(), student.getAge()) > 0;
    }

    public boolean addToCourse(Student student, Course course) {
        return jdbcTemplate.update(SQL_INSERT_STUDENT_COURSE, student.getId(), course.getId()) > 0;
    }

    public boolean update(Student student) {
        return jdbcTemplate.update(SQL_UPDATE_USER, student.getLogin(), student.getPassword(), student.getName(), student.getEmail(), student.getId()) > 0
                && jdbcTemplate.update(SQL_UPDATE_STUDENT, student.getAge(), student.getId()) > 0;
    }

    public boolean delete(Student student) {
        return jdbcTemplate.update(SQL_DELETE_USER, student.getId()) > 0;
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