package by.bsu.easytutor.service;

import by.bsu.easytutor.dao.TeacherDAO;
import by.bsu.easytutor.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherService {

    @Autowired
    private TeacherDAO teacherDAO;



}
