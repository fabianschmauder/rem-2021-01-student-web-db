package de.neuefische.studentwebdb.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String id;
    private String name;
    private String university;

}
