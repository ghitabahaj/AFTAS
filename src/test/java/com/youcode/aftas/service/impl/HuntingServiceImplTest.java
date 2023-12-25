package com.youcode.aftas.service.impl;



import com.youcode.aftas.entities.*;
import com.youcode.aftas.repository.HuntingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class HuntingServiceImplTest {

    @InjectMocks
    private HuntingServiceImpl huntingService;

    @Mock
    private HuntingRepository huntingRepository;



    private Fish createFishWithLevel(int levelValue) {
        Fish fish = new Fish();
        Level level = new Level();
        level.setPoints(levelValue);
        fish.setLevel(level);
        return fish;
    }

    @Test
    public void testCalculateParticipantScoreNoHunts() {
        Competition competition = new Competition();
        Member participant = new Member();

       when(huntingRepository.findByCompetitionAndMember(competition, participant)).thenReturn(null);

        int totalScore = huntingService.calculateParticipantScore(competition, participant);

        assertEquals(0, totalScore);
    }

    @Test
    public void testCalculateParticipantScoreWithNullFish() {

        Competition competition = new Competition();
        Member participant = new Member();

        Hunting huntWithNullFish = new Hunting();
        huntWithNullFish.setNumberOfFishes(3);
        huntWithNullFish.setFish(null);


       when(huntingRepository.findByCompetitionAndMember(competition, participant))
               .thenReturn(Collections.singletonList(huntWithNullFish));


        int totalScore = huntingService.calculateParticipantScore(competition, participant);

        assertEquals(0, totalScore);
    }


}