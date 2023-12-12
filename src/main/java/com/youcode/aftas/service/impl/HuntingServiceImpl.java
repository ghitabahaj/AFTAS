package com.youcode.aftas.service.impl;

import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.entities.Hunting;
import com.youcode.aftas.entities.Member;
import com.youcode.aftas.repository.CompetitionRepository;
import com.youcode.aftas.repository.FishRepository;
import com.youcode.aftas.repository.HuntingRepository;
import com.youcode.aftas.repository.MemberRepository;
import com.youcode.aftas.service.CompetitionService;
import com.youcode.aftas.service.FishService;
import com.youcode.aftas.service.HuntingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class HuntingServiceImpl implements HuntingService {
    private final HuntingRepository huntingRepository;
    private final FishRepository fishRepository;

    private final MemberRepository memberRepository;
    private final CompetitionRepository competitionRepository;

    @Autowired
    public HuntingServiceImpl(HuntingRepository huntingRepository,FishRepository fishRepository,  CompetitionRepository competitionRepository,MemberRepository memberRepository) {
        this.huntingRepository = huntingRepository;
        this.fishRepository = fishRepository;
        this.competitionRepository = competitionRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void addHunting(Long memberId, Long competitionId, Long fishId, String numberOfFishes) {

        Member participant = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Participant not found with ID: " + memberId));

        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new EntityNotFoundException("Competition not found with ID: " + competitionId));

        Fish fish = fishRepository.findById(fishId)
                .orElseThrow(() -> new EntityNotFoundException("Fish not found with ID: " + fishId));


        if (LocalDateTime.now().isBefore(competition.getStartTime())) {
            throw new IllegalStateException("Competition has not started yet.");
        }

        if (LocalDateTime.now().isAfter(competition.getEndTime())) {
            throw new IllegalStateException("Competition has already ended.");
        }

        if (!competition.getParticipants().contains(participant)) {
            throw new IllegalArgumentException("Participant is not registered for the competition.");
        }



        Hunting hunting = new Hunting();
        hunting.setNumberOfFishes(numberOfFishes);
        hunting.setMember(participant);
        hunting.setCompetition(competition);
        hunting.setFish(fish);


        huntingRepository.save(hunting);
    }

    public List<Hunting> getAllHuntsForParticipantInCompetition(Competition competition, Member participant) {
        return huntingRepository.findByCompetitionAndMember(competition, participant);
    }
}
