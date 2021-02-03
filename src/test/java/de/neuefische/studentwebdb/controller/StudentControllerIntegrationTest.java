package de.neuefische.studentwebdb.controller;

import de.neuefische.studentwebdb.db.StudentDb;
import de.neuefische.studentwebdb.model.Student;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentDb studentDb;

    @Test
    public void postStudentShouldAddStudentToDb(){
        //GIVEN
        String url = "http://localhost:" + port + "/student";
        Student studentToSend = new Student("1", "Frank", "cologne");

        //WHEN
        ResponseEntity<Student> response = restTemplate.postForEntity(url, studentToSend, Student.class);

        //THEN
        assertThat(response.getStatusCode() , is(HttpStatus.OK));
        assertThat(response.getBody() , is(new Student("1", "Frank", "cologne")));

        // check in db
        Optional<Student> savedStudent = studentDb.findById("1");
        assertThat(savedStudent.get(), is(new Student("1", "Frank", "cologne")));

        // or check list returns student
        ResponseEntity<Student[]> responseStudents = restTemplate.getForEntity(url, Student[].class);
        assertThat(responseStudents.getStatusCode() , is(HttpStatus.OK));
        assertThat(responseStudents.getBody() , arrayContaining(new Student("1", "Frank", "cologne")));
    }

}
