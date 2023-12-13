package com.youcode.aftas.service.impl;

import com.youcode.aftas.entities.*;
import com.youcode.aftas.handler.exception.CustomException;
import com.youcode.aftas.repository.*;
import com.youcode.aftas.service.HuntingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class HuntingServiceImpl implements HuntingService {

    @Autowired
    private final HuntingRepository huntingRepository;

    @Autowired
    private final FishRepository fishRepository;

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private final CompetitionRepository competitionRepository;

    @Autowired
    private final RankingRepository rankingRepository;


    public HuntingServiceImpl(HuntingRepository huntingRepository,FishRepository fishRepository,  CompetitionRepository competitionRepository,MemberRepository memberRepository,
                              RankingRepository rankingRepository) {
        this.huntingRepository = huntingRepository;
        this.fishRepository = fishRepository;
        this.competitionRepository = competitionRepository;
        this.memberRepository = memberRepository;
        this.rankingRepository = rankingRepository;
    }

    @Override
    public Hunting addHunting(Hunting hunting, Double weight) {

        Member member = memberRepository.findById(hunting.getMember().getId()).orElseThrow(() -> new EntityNotFoundException("Member not found with ID: " + hunting.getMember()));
        Competition competition = competitionRepository.findById(hunting.getCompetition().getId()).orElseThrow(() -> new EntityNotFoundException("Competition not found with ID: " + hunting.getCompetition()));
        Fish fish = fishRepository.findByName(hunting.getFish().getName());

        handleCommonHuntingLogic(member, competition, fish, weight);

        Hunting existingHunting = huntingRepository.findHuntingByFishAndMemberAndCompetition(fish, hunting.getMember(), hunting.getCompetition());
        if (existingHunting != null) {
            return huntingRepository.save(existingHunting);
        }

        return huntingRepository.save(hunting);
    }

    public List<Hunting> getAllHuntsForParticipantInCompetition(Competition competition, Member participant) {
        return huntingRepository.findByCompetitionAndMember(competition, participant);
    }


    @Override
    public int calculateParticipantScore(Competition competition, Member participant) {
        List<Hunting> participantHunts =getAllHuntsForParticipantInCompetition(competition, participant);

        int totalScore = 0;

        for (Hunting hunt : participantHunts) {
            Fish fish = hunt.getFish();
            Level fishLevel = fish.getLevel();

            int huntScore = fishLevel.getPoints();

            totalScore += huntScore;
        }

        return totalScore;
    }

    private void handleCommonHuntingLogic(Member participant, Competition competition, Fish fish, Double weight) {
        if (!LocalDate.now().isEqual(competition.getDate())) {
            throw new IllegalStateException("Hunting can only be recorded on the competition date.");
        }

        if (LocalTime.now().isBefore(competition.getStartTime())) {
            throw new IllegalStateException("Competition has not started yet.");
        }

        if (LocalTime.now().isAfter(competition.getEndTime())) {
            throw new IllegalStateException("Competition has already ended.");
        }

        Ranking ranking = rankingRepository.findByCompetitionAndMember(competition, participant);
        if (ranking == null) {
            throw new CustomException("This member is not registered for this competition", HttpStatus.UNAUTHORIZED);
        }

        if (weight < fish.getAverageWeight()) {
            throw new CustomException("The fish can't be counted as a hunt, as it doesn't meet the min weight required", HttpStatus.CONFLICT);
        }

        ranking.setScore(ranking.getRank() + fish.getLevel().getPoints());
        rankingRepository.save(ranking);
    }
}
