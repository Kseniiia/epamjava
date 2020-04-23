package by.bsu.easytutor.dao;

import java.sql.Connection;

public class DAOFactory {
    private Connection connection;

    public DAOFactory(Connection connection) {
        this.connection = connection;
    }

    public CourseDAO getCourseDAO() { return new CourseDAO(connection); }

    public SubjectDAO getSubjectDAO() {
        return new SubjectDAO(connection);
    }

    public StudentDAO getStudentDAO() {return new StudentDAO(connection); }
}