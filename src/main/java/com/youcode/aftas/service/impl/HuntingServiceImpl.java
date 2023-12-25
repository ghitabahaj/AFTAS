package com.youcode.aftas.service.impl;

import com.youcode.aftas.DTO.huntingDTO.huntingReqDTO;
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
import java.util.Optional;

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
    public Hunting addHunting(huntingReqDTO hunting) {

        Member member = memberRepository.findByIdentityNumber(hunting.IdentityNumber()).orElseThrow(() -> new EntityNotFoundException("Member not found with IdentityNumber: " + hunting.IdentityNumber()));
        Competition competition = competitionRepository.findByCode(hunting.competitionCode()).orElseThrow(() -> new EntityNotFoundException("Competition not found with ID: " + hunting.competitionCode()));
        Fish fish = fishRepository.findByName(hunting.name());
        handleCommonHuntingLogic(member, competition, fish, hunting.weight());

        Optional<Hunting> existingHunting = huntingRepository.findHuntingByFishAndMemberAndCompetition(fish , member, competition);
        Hunting huntsaved ;


        if (existingHunting.isPresent()){
              Hunting  hunting1 = existingHunting.get();

              Hunting updateHunting = Hunting.builder()
                      .id(hunting1.getId())
                      .fish(hunting1.getFish())
                      .numberOfFishes(hunting1.getNumberOfFishes() + 1)
                      .competition(hunting1.getCompetition())
                      .member(hunting1.getMember())
                      .build();
            huntsaved =  huntingRepository.save(updateHunting);

          }else {

              Hunting createHunting = Hunting.builder()
                      .fish(fish)
                      .competition(competition)
                      .member(member)
                      .numberOfFishes(1)
                      .build();

            huntsaved =  huntingRepository.save(createHunting);
          }

        Ranking ranking = rankingRepository.findByCompetitionAndMember(competition, member);
        if (ranking == null) {
            throw new CustomException("This member is not registered for this competition", HttpStatus.UNAUTHORIZED);
        }
        ranking.setScore(calculateParticipantScore(competition,member));
        rankingRepository.save(ranking);

        return  huntsaved;


    }

    public List<Hunting> getAllHuntsForParticipantInCompetition(Competition competition, Member participant) {
        return huntingRepository.findByCompetitionAndMember(competition, participant);
    }


    @Override
    public int  calculateParticipantScore(Competition competition, Member participant) {
        List<Hunting> participantHunts =getAllHuntsForParticipantInCompetition(competition, participant);

        int totalScore = 0;

        for (Hunting hunt : participantHunts) {
            Fish fish = hunt.getFish();
            Level fishLevel = fish.getLevel();
              int huntScore;
            huntScore = (int) (fishLevel.getPoints() * hunt.getNumberOfFishes());


            totalScore += huntScore;
            System.out.println("hhhh");
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


        if (weight < fish.getAverageWeight()) {
            throw new CustomException("The fish can't be counted as a hunt, as it doesn't meet the min weight required", HttpStatus.CONFLICT);
        }


    }
}
