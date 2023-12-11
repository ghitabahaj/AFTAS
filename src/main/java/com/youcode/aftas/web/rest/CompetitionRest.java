package com.youcode.aftas.web.rest;


import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.service.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1//competitions")
@RequiredArgsConstructor
public class CompetitionRest {
    private final CompetitionService competitionService;

    @PostMapping
    public ResponseEntity<?> addCompetition(@Valid @RequestBody Competition competition) {
        Competition savedCompetition = competitionService.addCompetition(competition);
        return ResponseEntity.ok(savedCompetition);
    }

}
