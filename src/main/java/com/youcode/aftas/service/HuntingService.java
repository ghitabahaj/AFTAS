package com.youcode.aftas.service;


import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Hunting;
import com.youcode.aftas.entities.Member;
import org.springframework.stereotype.Service;

@Service
public interface HuntingService {

     Hunting addHunting(Hunting hunting, Double weight);
     int calculateParticipantScore(Competition competition, Member participant);
}
