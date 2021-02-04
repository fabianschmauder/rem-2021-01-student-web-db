package de.neuefische.studentwebdb.covidapi;

import de.neuefische.studentwebdb.covidapi.model.CovidApiCountryStatusData;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CovidApiServiceTest {

    @Test
    public void getActiveCasesFromApi() {
        //GIVEN
        RestTemplate restTemplate = mock(RestTemplate.class);

        CovidApiCountryStatusData[] mockedData = {
                new CovidApiCountryStatusData(20, 10, 1, "NRW", "2021-02-02T00:00:00Z"),
                new CovidApiCountryStatusData(500, 10, 1, "Hamburg", "2021-02-02T00:00:00Z")};

        when(restTemplate.getForEntity(
                "https://api.covid19api.com/live/country/germany/status/confirmed/date/2021-02-01T13:13:30Z",
                CovidApiCountryStatusData[].class))
                .thenReturn(new ResponseEntity<>(mockedData, HttpStatus.OK));
        CovidApiService covidApiService = new CovidApiService(restTemplate);

        //WHEN
        CovidApiCountryStatusData[] activeCases = covidApiService.getActiveCases();

        //THEN
        assertThat(activeCases, Matchers.arrayContaining(
                new CovidApiCountryStatusData(20, 10, 1, "NRW", "2021-02-02T00:00:00Z"),
                new CovidApiCountryStatusData(500, 10, 1, "Hamburg", "2021-02-02T00:00:00Z")));
    }

}
