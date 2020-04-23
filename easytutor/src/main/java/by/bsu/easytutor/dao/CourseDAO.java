package by.bsu.easytutor.dao;

import by.bsu.easytutor.entity.Course;
import by.bsu.easytutor.entity.Subject;
import by.bsu.easytutor.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private Connection connection;

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

    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    public Course get(long id) throws SQLException {
        Course course = null;

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            course = buildCourse(resultSet);
        }

        return course;
    }

    public List<Course> getByStudentId(long studentId) throws SQLException {
        List<Course> courses = new ArrayList<Course>();

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_STUDENT_ID);
        preparedStatement.setLong(1, studentId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            courses.add(buildCourse(resultSet));
        }

        return courses;
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