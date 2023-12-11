package com.youcode.aftas.service.impl;

import com.youcode.aftas.entities.*;
import com.youcode.aftas.repository.CompetitionRepository;
import com.youcode.aftas.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;


    @Override
    public Competition addCompetition(Competition competition) {
        Optional<Competition> existingCompetition = competitionRepository.findById(competition.getId());
        if (existingCompetition.isPresent()) {
            Competition existing = existingCompetition.get();
            int totalFishes = 0;
            List<Ranking> existingRankings = existing.getRankings();
            for (Ranking ranking : existingRankings) {
                Member member = ranking.getMember();
                List<Hunting> huntings = member.getHuntings();
                for (Hunting hunting : huntings) {
                    Fish fish = hunting.getFish();
                    totalFishes += (int) (Integer.parseInt(hunting.getNumberOfFishes()) * fish.getAverageWeight());
                }
            }
            existing.setAmountOfFish(totalFishes);
            return competitionRepository.save(existing);
        } else {
            return competitionRepository.save(competition);
        }
    }
}
