package by.bsu.easytutor.controller;

import by.bsu.easytutor.entity.Course;
import by.bsu.easytutor.dto.StudentDTO;
import by.bsu.easytutor.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/students/{id}/courses")
    public String index(@PathVariable("id") long id, Model model) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");

        List<Course> courses = studentService.getCourses(id);

        model.addAttribute("courses", courses);

        return "studentCourses";
    }

    @PostMapping("/student")
    @ResponseBody
    public ResponseEntity<Object> create(@RequestBody StudentDTO student) {
        try{
            studentService.createStudent(student);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

