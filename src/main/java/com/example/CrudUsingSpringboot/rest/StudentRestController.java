package com.example.CrudUsingSpringboot.rest;

import com.example.CrudUsingSpringboot.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    //defne endpoint for "/student" -return a llist of student

    @GetMapping("/students")
    public List<Student> getStudents(){
        List<Student> theStudents=new ArrayList<>();
        theStudents.add(new Student("Ankit","Bisen"));
        theStudents.add(new Student("Manas","Patle"));
        theStudents.add(new Student("Arushi","Bisen"));
        theStudents.add(new Student("Darshu","Bisen"));
        return theStudents;
    }
}
