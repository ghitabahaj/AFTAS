package com.youcode.aftas.service.impl;

import com.youcode.aftas.DTO.competitionDto.CompetitionDTO;
import com.youcode.aftas.DTO.competitionDto.CompetitionDetailsDto;
import com.youcode.aftas.entities.*;
import com.youcode.aftas.repository.CompetitionRepository;
import com.youcode.aftas.repository.RankingRepository;
import com.youcode.aftas.service.CompetitionService;
import com.youcode.aftas.service.RankingService;
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
    private final RankingService rankingService;


    @Override
    public Competition addCompetition(CompetitionDTO competition) {

        if (competition.date().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Competition date must be today or a future date.");
        }

        if (!competition.startTime().equals(competition.endTime()) && competition.endTime().isAfter(competition.startTime())) {
            if (!isCompetitionExistsOnSameDay(competition.date())) {
                if (competition.id() == null) {
                    String locationAbbreviation = competition.location().substring(0, Math.min(competition.location().length(), 3));
                    String formattedDate = competition.date().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
                    String generatedName = locationAbbreviation + "-" + formattedDate;
                    Competition competition1 = Competition.builder()
                            .amount(competition.amount())
                            .code(generatedName)
                            .location(competition.location())
                            .endTime(competition.endTime())
                            .startTime(competition.startTime())
                            .numberOfParticipants(competition.numberOfParticipants())
                            .date(competition.date())
                            .build();
                    return competitionRepository.save(competition1);

                }
            } else {
                throw new IllegalArgumentException("There is already a competition scheduled on the same day.");
            }
        } else {
            throw new IllegalArgumentException("Invalid start and end times for the competition.");
        }
        Competition competition1 = Competition.builder()
                .amount(competition.amount())
                .code(competition.code())
                .location(competition.location())
                .endTime(competition.endTime())
                .startTime(competition.startTime())
                .numberOfParticipants(competition.numberOfParticipants())
                .date(competition.date())
                .build();
        return competitionRepository.save(competition1);

    }

    @Override
    public Competition findByCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Code should not be Empty or null");
        }

        if (competitionRepository.findByCode(code).isEmpty()) {
            throw new IllegalArgumentException("Competition with code " + code + " could not be found");
        }

        return competitionRepository.findByCode(code).orElse(null);
    }

    @Override
    public Page<Competition> findAllCompetitions(Pageable pageable) {

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
        List<Competition> competitions = competitionRepository.findAll();

        competitions.forEach(competition -> {
            List<Ranking> rankings = rankingService.sortMemberWithPoints(competition.getId());
            competition.setRankings(rankings);
        });

        return competitions;
    }
    @Override
    public List<Competition> availableCompetitions() {
        return competitionRepository.findAvailableCompetitions(LocalDate.now());
    }

    @Override
    public Competition getCompetitionOfTheDay() {
        return competitionRepository.findCompetitionByDate(LocalDate.now());


    }



}
