package com.example.studentpractice.web;

import com.example.studentpractice.entities.Student;
import com.example.studentpractice.respositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(path = "/index")
    public String students(Model model){

        List<Student> students = studentRepository.findAll();
        model.addAttribute("listStudents", students);

        return "students";
    }
}
