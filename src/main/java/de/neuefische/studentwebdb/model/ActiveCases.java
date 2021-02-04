package de.neuefische.studentwebdb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveCases {

    private String date;
    private String province;
    private int cases;

}
