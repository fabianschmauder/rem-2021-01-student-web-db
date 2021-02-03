package de.neuefische.studentwebdb.service;

import de.neuefische.studentwebdb.db.StudentDb;
import de.neuefische.studentwebdb.model.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class StudentsServiceTest {


    @Test
    public void searchForStudent() {
        //GIVEN
        StudentDb studentDb = mock(StudentDb.class);
        when(studentDb.listStudents()).thenReturn(List.of(
                new Student("1", "Frank", "hamburg"),
                new Student("2", "Jan", "cologne"),
                new Student("3", "Martin Jan", "hamburg"),
                new Student("4", "Frank", "hamburg")
        ));


        StudentsService studentsService = new StudentsService(studentDb);

        //WHEN
        List<Student> result = studentsService.searchStudents("Ja");

        //THEN
        assertThat(result, containsInAnyOrder(
                new Student("2", "Jan", "cologne"),
                new Student("3", "Martin Jan", "hamburg")));


    }

    @Test
    public void addStudentShouldCallAddStudent(){
        //GIVEN
        StudentDb studentDb = mock(StudentDb.class);
        Student studentToAdd = new Student("1", "jan", "cologne");
        StudentsService studentsService = new StudentsService(studentDb);
        when(studentDb.add(studentToAdd)).thenReturn(studentToAdd);


        //WHEN
        Student result = studentsService.addStudent(studentToAdd);

        //THEN
        assertThat(result, is(new Student("1", "jan", "cologne")));
        verify(studentDb).add(new Student("1", "jan", "cologne"));

    }
}
