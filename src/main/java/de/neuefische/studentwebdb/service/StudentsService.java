package de.neuefische.studentwebdb.service;

import de.neuefische.studentwebdb.db.StudentDb;
import de.neuefische.studentwebdb.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {

    private final StudentDb studentDb;

    @Autowired
    public StudentsService(StudentDb studentDb) {
        this.studentDb = studentDb;
    }


    public List<Student> searchStudents(String search) {
        List<Student> result = new ArrayList<>();
        List<Student> students = this.studentDb.listStudents();
        for (Student student : students) {
            if (student.getName().contains(search)) {
                result.add(student);
            }
        }

        return result;
    }

    public Optional<Student> getStudentById(String id) {
       return this.studentDb.findById(id);
    }

    public Student addStudent(Student student) {
        if(student.getName().isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"Student name is empty");
        }
        return this.studentDb.add(student);
    }


    public List<Student> getStudentsByUniversity(String university){
        List<Student> result = new ArrayList<>();
        List<Student> students = this.studentDb.listStudents();
        for (Student student : students) {
            if(student.getUniversity().equals(university)){
                result.add(student);
            }
        }

        return result;
    }
}
