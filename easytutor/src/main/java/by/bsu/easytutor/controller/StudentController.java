package by.bsu.easytutor.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class StudentController {
    private s

    @RequestMapping("/students/")
    public String index() {
        return "Good luck, Ksusha";
    }
}
