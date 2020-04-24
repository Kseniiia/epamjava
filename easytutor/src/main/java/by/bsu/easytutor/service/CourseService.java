package by.bsu.easytutor.service;

import by.bsu.easytutor.dao.CourseDAO;
import by.bsu.easytutor.entity.Course;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseService {

    @Autowired
    private CourseDAO courseDAO;

    private final Logger logger = LogManager.getLogger();

    public void createCourse(Course course) {
        logger.info("Create course");

        if (!courseDAO.save(course)) {
            throw new IllegalArgumentException();
        }
    }
}
