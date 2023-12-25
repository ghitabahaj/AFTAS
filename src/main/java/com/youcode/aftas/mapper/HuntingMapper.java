package com.youcode.aftas.mapper;

import com.youcode.aftas.DTO.huntingDTO.huntingResDTO;
import com.youcode.aftas.entities.Hunting;
import lombok.Builder;



public class HuntingMapper {
    public static huntingResDTO mapToDto(Hunting hunting){
        return huntingResDTO.builder()
                .competition(hunting.getCompetition())
                .member(hunting.getMember())
                .fish(hunting.getFish())
                .numberOfFishes(hunting.getNumberOfFishes())
                .build();
    }
}
