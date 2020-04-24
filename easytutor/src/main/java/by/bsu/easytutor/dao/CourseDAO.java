package by.bsu.easytutor.dao;

import by.bsu.easytutor.entity.Course;
import by.bsu.easytutor.entity.Subject;
import by.bsu.easytutor.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BY_ID = "SELECT * " +
                                                   "FROM Courses c " +
                                                     "LEFT JOIN Users u ON (c.teacher_id = u.id) " +
                                                     "LEFT JOIN Teachers t ON (u.id = t.user_id) " +
                                                     "LEFT JOIN Subjects s ON (c.subject_id = s.id) " +
                                                   "WHERE c.id = ?";

    private static final String SQL_SELECT_BY_STUDENT_ID = "SELECT * " +
                                                           "FROM Courses c " +
                                                             "LEFT JOIN Users u ON (c.teacher_id = u.id) " +
                                                             "LEFT JOIN Teachers t ON (u.id = t.user_id) " +
                                                             "LEFT JOIN Subjects s ON (c.subject_id = s.id) " +
                                                           "WHERE c.id IN (SELECT course_id FROM StudentsCourses WHERE student_id = ?)";

    public Optional<Course> get(long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, (rs, rowNumber) -> Optional.of(buildCourse(rs)));
    }

    public List<Course> getByStudentId(long studentId) throws SQLException {
        return jdbcTemplate.query(SQL_SELECT_BY_STUDENT_ID, new Object[]{studentId}, (rs, rowNumber) -> buildCourse(rs));
    }

    private Course buildCourse(ResultSet resultSet) throws SQLException {
        Course course = new Course();

        course.setId(resultSet.getLong(1));
        course.setName(resultSet.getString(2));

        Teacher teacher = new Teacher();

        teacher.setId(resultSet.getLong(5));
        teacher.setLogin(resultSet.getString(6));
        teacher.setPassword(resultSet.getString(7));
        teacher.setName(resultSet.getString(8));
        teacher.setEmail(resultSet.getString(9));
        teacher.setEducation(resultSet.getString(11));
        teacher.setExperience(resultSet.getString(12));

        course.setTeacher(teacher);

        Subject subject = new Subject();

        subject.setId(resultSet.getLong(13));
        subject.setName(resultSet.getString(14));

        course.setSubject(subject);

        return course;
    }
}