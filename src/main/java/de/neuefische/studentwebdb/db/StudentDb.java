package de.neuefische.studentwebdb.db;


import de.neuefische.studentwebdb.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDb {

    private final List<Student> students = new ArrayList<>();


    public List<Student> listStudents(){
        return students;
    }

    public Optional<Student> findById(String id){
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }

    public Student add(Student student){
        students.add(student);
        return student;
    }
}
