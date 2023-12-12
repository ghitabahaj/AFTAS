package com.youcode.aftas.service;


import com.youcode.aftas.entities.Competition;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

@Service
public interface CompetitionService {

    Competition addCompetition(Competition competition);
    Optional<Competition> findByCode(String code);
    Page<Competition> findAll(Pageable pageable);


}
