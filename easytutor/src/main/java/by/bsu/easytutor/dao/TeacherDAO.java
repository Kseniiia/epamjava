package by.bsu.easytutor.dao;

import by.bsu.easytutor.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TeacherDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM Users LEFT JOIN Teachers ON (id = user_id) WHERE id = ?";
    private static final String SQL_INSERT_USER = "INSERT INTO Users VALUES (NULL, ?, ?, ?, ?)";
    private static final String SQL_INSERT_TEACHER = "INSERT INTO Teachers VALUES((SELECT id FROM Users WHERE login = ?), ?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE USERS SET login = ?, password = ?, name = ?, email = ? WHERE id = ?";
    private static final String SQL_UPDATE_TEACHER = "UPDATE Teachers SET education = ?, experience = ? WHERE user_id = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM Users WHERE id = ?";


    public Teacher get(long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, (rs, rowNumber) -> buildTeacher(rs));
    }

    public boolean save(Teacher teacher) {
        return jdbcTemplate.update(SQL_INSERT_USER, teacher.getLogin(), teacher.getPassword(), teacher.getName(), teacher.getEmail()) > 0
                && jdbcTemplate.update(SQL_INSERT_TEACHER, teacher.getLogin(), teacher.getEducation(), teacher.getExperience()) > 0;
    }

    public boolean update(Teacher teacher) {
        return jdbcTemplate.update(SQL_UPDATE_USER, teacher.getLogin(), teacher.getPassword(), teacher.getName(), teacher.getEmail(), teacher.getId()) > 0
                && jdbcTemplate.update(SQL_UPDATE_TEACHER, teacher.getEducation(), teacher.getExperience(), teacher.getId()) > 0;
    }

    public boolean delete(Teacher teacher) {
        return jdbcTemplate.update(SQL_DELETE_USER, teacher.getId()) > 0;
    }

    private Teacher buildTeacher(ResultSet resultSet) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("id"));
        teacher.setLogin(resultSet.getString("login"));
        teacher.setPassword(resultSet.getString("password"));
        teacher.setName(resultSet.getString("name"));
        teacher.setEmail(resultSet.getString("email"));
        teacher.setEducation(resultSet.getString("education"));
        teacher.setExperience(resultSet.getString("experience"));

        return teacher;
    }
}
