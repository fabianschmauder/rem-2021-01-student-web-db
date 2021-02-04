package de.neuefische.studentwebdb.service;

import de.neuefische.studentwebdb.covidapi.CovidApiService;
import de.neuefische.studentwebdb.covidapi.model.CovidApiCountryStatusData;
import de.neuefische.studentwebdb.model.ActiveCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CovidService {

    private final CovidApiService covidApiService;

    @Autowired
    public CovidService(CovidApiService covidApiService) {
        this.covidApiService = covidApiService;
    }

    public Optional<ActiveCases> getActiveCasesHamburg(){

        CovidApiCountryStatusData[] activeCases = covidApiService.getActiveCases();

        for (CovidApiCountryStatusData activeCase : activeCases) {
            if(activeCase.getProvince().equals("Hamburg")){
                return Optional.of(new ActiveCases(
                        activeCase.getDate(),
                        activeCase.getProvince(),
                        activeCase.getActive()
                ));
            }
        }
        return Optional.empty();
    }
}
