package de.neuefische.studentwebdb.controller;

import de.neuefische.studentwebdb.model.ActiveCases;
import de.neuefische.studentwebdb.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("covid")
public class ActiveCasesController {

    private final CovidService covidService;

    @Autowired
    public ActiveCasesController(CovidService covidService) {
        this.covidService = covidService;
    }

    @GetMapping
    public ActiveCases getActiveCasesHamburg(){
        Optional<ActiveCases> activeCasesHamburg = covidService.getActiveCasesHamburg();
        if(activeCasesHamburg.isPresent()){
            return activeCasesHamburg.get();
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}
