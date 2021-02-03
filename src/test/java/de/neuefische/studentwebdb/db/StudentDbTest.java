package de.neuefische.studentwebdb.db;


import de.neuefische.studentwebdb.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

class StudentDbTest {

    @Test
    @DisplayName("add first student to db")
    public void addStudentShouldAddStudentToDb(){
        //GIVEN
        StudentDb studentDb = new StudentDb();
        Student student = new Student("1", "Frank", "hamburg");

        //WHEN
        studentDb.add(student);
        List<Student> students = studentDb.listStudents();

        //THEN
        assertThat(students, contains(new Student("1", "Frank", "hamburg")));
    }


    @Test
    @DisplayName("add more than one student to db")
    public void addStudentShouldAddSecondStudentToDb(){
        //GIVEN
        StudentDb studentDb = new StudentDb();
        studentDb.add(new Student("1", "Frank", "hamburg"));
        Student student = new Student("2", "Jan", "cologne");

        //WHEN
        studentDb.add(student);
        List<Student> students = studentDb.listStudents();

        //THEN students contains in any order
        assertThat(students, containsInAnyOrder(
                new Student("2", "Jan", "cologne"),
                new Student("1", "Frank", "hamburg")));
    }


    @Test
    @DisplayName("findById should return student when student with id exists")
    public void findByIdFindExistingStudent(){
        //GIVEN
        StudentDb studentDb = new StudentDb();
        studentDb.add(new Student("1", "Frank", "hamburg"));
        studentDb.add(new Student("2", "Jan", "cologne"));

        //WHEN
        Optional<Student> studentOptional = studentDb.findById("2");

        //THEN
        assertThat(studentOptional.get(), is(new Student("2", "Jan", "cologne")));
    }


    @Test
    @DisplayName("findById should return empty optional when id not found")
    public void findByIdShouldEmptyOptional(){
        //GIVEN
        StudentDb studentDb = new StudentDb();
        studentDb.add(new Student("1", "Frank", "hamburg"));
        studentDb.add(new Student("2", "Jan", "cologne"));

        //WHEN
        Optional<Student> studentOptional = studentDb.findById("5");

        //THEN
        assertThat(studentOptional.isEmpty(), is(true));
    }
}
