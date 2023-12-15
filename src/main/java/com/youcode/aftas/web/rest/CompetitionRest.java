package com.youcode.aftas.web.rest;


import com.youcode.aftas.DTO.CompetitionDTO;
import com.youcode.aftas.DTO.FishDTO.response.FishResDto;
import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.mapper.CompetitionMapper;
import com.youcode.aftas.service.CompetitionService;
import com.youcode.aftas.utils.Response;
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
    public ResponseEntity<Response <List<CompetitionDTO>>> getAllCompetitions() {
        Response <List<CompetitionDTO>> response = new Response<>();
        List<Competition> competitions = competitionService.findAll();
        response.setResult(competitions.stream()
                .map(CompetitionMapper::mapToDto)
                .toList());
        response.setMessage("Competitions fetched successfully");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<Response<CompetitionDTO>> addCompetition(@Valid @RequestBody() CompetitionDTO competition) {
        Response<CompetitionDTO> response = new Response<>();
        Competition addedCompetition = competitionService.addCompetition(competition);
        response.setResult(CompetitionMapper.mapToDto(addedCompetition));
        response.setMessage("Competition added successfully");
        return  ResponseEntity.ok(response);
    }

}
