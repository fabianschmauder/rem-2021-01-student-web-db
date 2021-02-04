package de.neuefische.studentwebdb.covidapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CovidApiCountryStatusData {

    @JsonProperty("Active")
    private int active;
    @JsonProperty("Deaths")
    private int deaths;
    @JsonProperty("Recovered")
    private int recovered;
    @JsonProperty("Province")
    private String province;
    @JsonProperty("Date")
    private String date;

}
