package com.youcode.aftas.service;


import com.youcode.aftas.DTO.competitionDto.CompetitionDTO;
import com.youcode.aftas.DTO.competitionDto.CompetitionDetailsDto;
import com.youcode.aftas.entities.Competition;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.List;

@Service
public interface CompetitionService {

    Competition addCompetition(CompetitionDTO competition);
    Competition findByCode(String code);
    Page<Competition> findAllCompetitions(Pageable pageable);

    boolean isCompetitionExistsOnSameDay(LocalDate date);

    Competition updateCompetition(Competition competition);

    Competition findById(Long id);

    List<Competition> findAll();

    List<Competition> availableCompetitions();

    Competition getCompetitionOfTheDay();


}
