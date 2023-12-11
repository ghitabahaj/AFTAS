package com.youcode.aftas.service;


import com.youcode.aftas.entities.Competition;
import org.springframework.stereotype.Service;

@Service
public interface CompetitionService {

    Competition addCompetition(Competition competition);
}
