package com.youcode.aftas.service.impl;

import com.youcode.aftas.entities.*;
import com.youcode.aftas.repository.CompetitionRepository;
import com.youcode.aftas.service.CompetitionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;


    @Override
    public Competition addCompetition(Competition competition) {

        if (competition.getDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Competition date must be today or a future date.");
        }

        if (!competition.getStartTime().equals(competition.getEndTime()) && competition.getEndTime().isAfter(competition.getStartTime())) {
            if (!isCompetitionExistsOnSameDay(competition.getDate())) {
                if (competition.getId() == null) {
                    String locationAbbreviation = competition.getLocation().substring(0, Math.min(competition.getLocation().length(), 3));
                    String formattedDate = competition.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
                    String generatedName = locationAbbreviation + "-" + formattedDate;
                    competition.setCode(generatedName);
                    return competitionRepository.save(competition);
                }
            } else {
                throw new IllegalArgumentException("There is already a competition scheduled on the same day.");
            }
        } else {
            throw new IllegalArgumentException("Invalid start and end times for the competition.");
        }

        return competition;
    }

    @Override
    public Optional<Competition> findByCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Code should not be Empty or null");
        }

        if (competitionRepository.findByCode(code).isEmpty()) {
            throw new IllegalArgumentException("Competition with code " + code + " could not be found");
        }

        return competitionRepository.findByCode(code);
    }

    @Override
    public Page<Competition> findAll(Pageable pageable) {
        return competitionRepository.findAll(pageable);
    }


    public boolean isCompetitionExistsOnSameDay(LocalDate date){

        return  competitionRepository.existsByDate(date);
    }

    @Override
    public Competition updateCompetition(Competition competition) {
        if (competition == null || competition.getId() == null) {
            throw new IllegalArgumentException("Competition or competition ID cannot be null.");
        }

        Optional<Competition> existingCompetition = competitionRepository.findById(competition.getId());
        if (existingCompetition.isEmpty()) {
            throw new EntityNotFoundException("Competition with ID " + competition.getId() + " not found.");
        }

        Competition existing = existingCompetition.get();
        existing.setCode(competition.getCode());
        existing.setDate(competition.getDate());
        existing.setStartTime(competition.getStartTime());
        existing.setEndTime(competition.getEndTime());
        existing.setNumberOfParticipants(competition.getNumberOfParticipants());
        existing.setLocation(competition.getLocation());
        existing.setAmount(competition.getAmount());
        existing.setRankings(competition.getRankings());

        return competitionRepository.save(existing);
    }

    @Override
    public Competition findById(Long id) {
        return competitionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Competition> findAll() {
        return competitionRepository.findAll();
    }

}
