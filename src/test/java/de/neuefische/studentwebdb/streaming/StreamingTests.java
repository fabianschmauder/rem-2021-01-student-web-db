package de.neuefische.studentwebdb.streaming;

import de.neuefische.studentwebdb.model.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamingTests {
    List<Student> students = List.of(
            new Student("1", "Frank", "cologne"),
            new Student("2", "Jochen", "hamburg"),
            new Student("3", "Jan", "hamburg"),
            new Student("4", "Peter", "cologne")


    );

    @Test
    public void forEachStreaming(){

        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("the same as -----");

        students.stream().forEach(student -> {
            System.out.println(student);
        });

    }

    @Test
    public void filterStudents(){
        List<Student> allStudentsInHamburg = new ArrayList<>();

        for (Student student : students) {
            if(student.getUniversity().equals("hamburg")){
                allStudentsInHamburg.add(student);
            }
        }
        System.out.println(allStudentsInHamburg);
        System.out.println("the same as -----");

        List<Student> allStudentsInHamburgStream = students.stream()
                .filter(student -> student.getUniversity().equals("hamburg"))
                .collect(Collectors.toList());
        System.out.println(allStudentsInHamburgStream);
    }


    @Test
    public void filterChaningStudents(){
        List<Student> allStudentsInHamburgStream = students.stream()
                .filter(student -> student.getUniversity().equals("hamburg"))
                .filter(student -> student.getName().startsWith("Ja"))
                .collect(Collectors.toList());
        System.out.println(allStudentsInHamburgStream);
    }



    @Test
    public void mapStudents(){
        List<String> allStudentsNameInHamburg = students.stream()
                .filter(student -> student.getUniversity().equals("hamburg"))
                .map(student -> student.getName())
                .collect(Collectors.toList());
        System.out.println(allStudentsNameInHamburg);
    }





}
