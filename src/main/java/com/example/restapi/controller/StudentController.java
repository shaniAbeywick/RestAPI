package com.example.restapi.controller;

import com.example.restapi.exception.ResourceNotFoundException;
import com.example.restapi.model.Student;
import com.example.restapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    //Create a student

    @PostMapping //create resource
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    //get student by ID
    @GetMapping("{id}")
    public ResponseEntity<Student>getStudentsByID(@PathVariable long id){
        Student student =studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("There is no students from this id"+id));
        return ResponseEntity.ok(student);
    }

    //update student details
    @PutMapping("{id}") //update resource
    public ResponseEntity<Student>updateStudent(@PathVariable long id,@RequestBody Student studentDetails){
        Student updateStudent = studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("There is no student from this id" +id));

        updateStudent.setName(studentDetails.getName());
        updateStudent.setAge(studentDetails.getAge());
        updateStudent.setEmail(studentDetails.getEmail());

        studentRepository.save(updateStudent);

        return ResponseEntity.ok(updateStudent);

    }

    //delete a student
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long id){
        Student student =studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("There is no student from this id" +id));
        studentRepository.delete(student);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
