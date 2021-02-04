package de.neuefische.studentwebdb.controller;

import de.neuefische.studentwebdb.covidapi.CovidApiService;
import de.neuefische.studentwebdb.covidapi.model.CovidApiCountryStatusData;
import de.neuefische.studentwebdb.model.ActiveCases;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActiveCasesControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CovidApiService covidApiService;

    @Test
    public void getActiveCasesShouldReturnResultFromApi(){
        //GIVEN
        CovidApiCountryStatusData[] mockedData = {
                new CovidApiCountryStatusData(20, 10, 1, "NRW", "2021-02-02T00:00:00Z"),
                new CovidApiCountryStatusData(300, 10, 1, "Hamburg", "2021-02-02T00:00:00Z")};

        when(covidApiService.getActiveCases()).thenReturn(
                mockedData
        );

        //WHEN
        ResponseEntity<ActiveCases> response = restTemplate.getForEntity("http://localhost:" + port + "/covid", ActiveCases.class);


        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new ActiveCases(
                "2021-02-02T00:00:00Z",
                "Hamburg",
                300) ));
    }

}
