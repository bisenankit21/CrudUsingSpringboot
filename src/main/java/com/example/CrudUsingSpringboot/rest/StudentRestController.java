package com.example.CrudUsingSpringboot.rest;

import com.example.CrudUsingSpringboot.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;
    //define @PostConstruct to load the student data ....only once
    @PostConstruct
    /* The @PostConstruct annotation indicates that a method should be called after a bean has been initialized.
       It’s typically used to perform any initialization tasks required before the bean can be used.
    */
    public void loadData(){
        theStudents=new ArrayList<>();
        theStudents.add(new Student("Ankit","Bisen"));
        theStudents.add(new Student("Manas","Patle"));
        theStudents.add(new Student("Arushi","Bisen"));
        theStudents.add(new Student("Darshu","Bisen"));
    }
    // defne endpoint for "/student" -return a llist of student

    @GetMapping("/students")
    public List<Student> getStudents(){
              return theStudents;

    }

    //define end points for "/students/{studentId} ---rettirveing a single student
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        //check for exception against the lst size
        if((studentId>=theStudents.size())||(studentId<0)){
            throw new StudentNotFoundException("Student id is not found--" +studentId);
        }



        //juts index into the list
        return theStudents.get(studentId);
    }

    //Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        //create a studemtErrorResponse
        StudentErrorResponse error=new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        //return ResponseEntity
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
