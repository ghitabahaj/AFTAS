package com.youcode.aftas.service;


import com.youcode.aftas.entities.Competition;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface CompetitionService {

    Competition addCompetition(Competition competition);
    Optional<Competition> findByCode(String code);
    Page<Competition> findAll(Pageable pageable);

    boolean isCompetitionExistsOnSameDay(LocalDate date);

    Competition updateCompetition(Competition competition);

    Competition findById(Long id);

    List<Competition> findAll();


}
