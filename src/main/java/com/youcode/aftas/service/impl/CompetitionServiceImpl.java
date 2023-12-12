package com.youcode.aftas.service.impl;

import com.youcode.aftas.entities.*;
import com.youcode.aftas.repository.CompetitionRepository;
import com.youcode.aftas.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;


    @Override
    public Competition addCompetition(Competition competition) {

        if (!competition.getStartTime().equals(competition.getEndTime()) && competition.getEndTime().isAfter(competition.getStartTime())) {

             if (competition.getDate().isAfter(LocalDate.now()) && !competition.getDate().isBefore(LocalDate.now()) ) {

                  if (!isCompetitionExistsOnSameDay(competition.getDate())){

                      Optional<Competition> existingCompetition = competitionRepository.findById(competition.getId());

                      if (existingCompetition.isPresent()) {

                          Competition existing = existingCompetition.get();



                          return competitionRepository.save(existing);

                      } else {

                          return competitionRepository.save(competition);

                      }
                  } else {
                      throw new IllegalArgumentException("There is already a competition scheduled on the same day.");
                  }
             } else {
                 throw new IllegalArgumentException("Competition date must be today or a future date.");
             }
        } else {
            throw new IllegalArgumentException("Invalid start and end times for the competition.");
        }

    }

    @Override
    public Optional<Competition> findByCode(String code) {
        return Optional.empty();
    }

    @Override
    public Page<Competition> findAll(Pageable pageable) {
        return competitionRepository.findAll(pageable);
    }


    public boolean isCompetitionExistsOnSameDay(LocalDate date){
        return  competitionRepository.existsByDate(date);
    }
}
