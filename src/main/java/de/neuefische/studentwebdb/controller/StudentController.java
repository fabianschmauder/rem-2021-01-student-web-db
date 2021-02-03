package de.neuefische.studentwebdb.controller;

import de.neuefische.studentwebdb.model.Student;
import de.neuefische.studentwebdb.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {


    private final StudentsService studentsService;

    @Autowired
    public StudentController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping
    public List<Student> listStudents(@RequestParam Optional<String> search){
        return studentsService.searchStudents(search.orElse(""));
    }

    @GetMapping("{id}")
    public Student getStudentById(@PathVariable String id){
        Optional<Student> studentOptional = studentsService.getStudentById(id);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "student with id "+ id+ " not found");
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentsService.addStudent(student);
    }

}
