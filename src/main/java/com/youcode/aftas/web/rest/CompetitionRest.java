package com.youcode.aftas.web.rest;


import com.youcode.aftas.DTO.competitionDto.CompetitionDTO;
import com.youcode.aftas.DTO.RankingDTO;
import com.youcode.aftas.DTO.competitionDto.CompetitionDetailsDto;
import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Ranking;
import com.youcode.aftas.mapper.CompetitionMapper;
import com.youcode.aftas.mapper.RankingMapper;
import com.youcode.aftas.service.CompetitionService;
import com.youcode.aftas.service.RankingService;
import com.youcode.aftas.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/competitions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CompetitionRest {
    private final CompetitionService competitionService;
    private final RankingService rankingService;

    @GetMapping("/")
    public ResponseEntity<Response <List<CompetitionDetailsDto>>> getAllCompetitions() {
        Response <List<CompetitionDetailsDto>> response = new Response<>();
        List<Competition> competitions = competitionService.findAll();

        response.setResult(competitions.stream()
                .map(CompetitionDetailsDto::toCompetitionDetailsDto)
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
    @GetMapping("/{competitionId}/rankings")
    public ResponseEntity<Response<List<RankingDTO>>> getCompetitionRankings(@PathVariable Long competitionId) {
        Response<List<RankingDTO>> response = new Response<>();
        List<Ranking> rankings = rankingService.sortMemberWithPoints(competitionId);
        response.setResult(rankings.stream()
                .map(RankingMapper::mapToDto)
                .toList());
        response.setMessage("Rankings fetched successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/available")
    public ResponseEntity<Response <List<CompetitionDTO>>> getAvailable(){
        Response <List<CompetitionDTO>> response = new Response<>();
        List<Competition> competitions = competitionService.availableCompetitions();
        response.setResult(competitions.stream()
                .map(CompetitionMapper::mapToDto)
                .toList());
        response.setMessage("Competitions fetched successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/today")
    public ResponseEntity<Response <CompetitionDetailsDto>> getCompetitionOfTheDay() {
        Response <CompetitionDetailsDto> response = new Response<>();
        Competition competitionOfTheDay = competitionService.getCompetitionOfTheDay();
        if (competitionOfTheDay != null) {
            response.setResult(CompetitionDetailsDto.toCompetitionDetailsDto(competitionOfTheDay));
            response.setMessage("Competition found");
            return ResponseEntity.ok(response);
        }else{
            response.setResult(null);
            response.setMessage(" there's no Competition found");
            return ResponseEntity.ok(response);
        }

    }

    @GetMapping("/paged")
    public ResponseEntity<Page<CompetitionDetailsDto>> getAllCompetitionsPaged(Pageable pageable) {
        Page<Competition> competitions = competitionService.findAllCompetitions(pageable);

        Page<CompetitionDetailsDto> competitionsDTO = new PageImpl<>(competitions.stream()
                .map(CompetitionDetailsDto::toCompetitionDetailsDto)
                .toList(), pageable, competitions.getTotalElements());

        return new ResponseEntity<>(competitionsDTO, HttpStatus.OK);
    }


}
