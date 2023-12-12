package com.youcode.aftas.service;


import com.youcode.aftas.entities.Hunting;
import org.springframework.stereotype.Service;

@Service
public interface HuntingService {

     void addHunting(Long memberId, Long competitionId, Long fishId, String numberOfFishes);
}
