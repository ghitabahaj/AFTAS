package com.youcode.aftas.web.rest;


import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.service.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/competitions")
@RequiredArgsConstructor
public class CompetitionRest {
    private final CompetitionService competitionService;

    @GetMapping("/")
    public ResponseEntity<?> getAllCompetitions() {
        List<Competition> competitions = competitionService.findAll();
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCompetition(@Valid @RequestBody()Competition competition) {
        Competition addedCompetition = competitionService.addCompetition(competition);
        return new ResponseEntity<>(addedCompetition, HttpStatus.OK);
    }

}
