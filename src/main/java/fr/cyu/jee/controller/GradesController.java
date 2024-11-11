package fr.cyu.jee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/grades")
public class GradesController {

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEditPage() {
        return "teacher_grades";
    }
}
