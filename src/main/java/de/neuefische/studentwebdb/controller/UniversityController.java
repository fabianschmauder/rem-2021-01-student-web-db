package de.neuefische.studentwebdb.controller;

import de.neuefische.studentwebdb.model.Student;
import de.neuefische.studentwebdb.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("university")
public class UniversityController {

    private final StudentsService studentsService;

    @Autowired
    public UniversityController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping("{university}/students")
    public List<Student> studentList(@PathVariable String university){
        return studentsService.getStudentsByUniversity(university);
    }

}
