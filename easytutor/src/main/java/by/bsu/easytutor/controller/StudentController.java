package by.bsu.easytutor.controller;

import by.bsu.easytutor.entity.Course;
import by.bsu.easytutor.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/students/{id}/courses")
    @ResponseBody
    public String index(@PathVariable("id") long id) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");

        List<Course> courses = studentService.getCourses(id);

        String html = "<table><thead><tr><th>Id</th><th>Name</th></tr></tbody>";
        for (Course course : courses) {
            html += "<tr><td>" + course.getId() + "</td><td>" + course.getName() + "</td></tr>";
        }
        html += "</tbody></table>";

        return html;
    }
}

