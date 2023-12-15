package com.youcode.aftas.mapper;

import com.youcode.aftas.DTO.competitionDto.CompetitionDTO;
import com.youcode.aftas.entities.Competition;

public class CompetitionMapper {
    public static CompetitionDTO mapToDto(Competition competition){
        return CompetitionDTO.builder()
                .id(competition.getId())
                .code(competition.getCode())
                .date(competition.getDate())
                .startTime(competition.getStartTime())
                .amount(competition.getAmount())
                .endTime(competition.getEndTime())
                .location(competition.getLocation())
                .numberOfParticipants(competition.getNumberOfParticipants())
                .build();
    }
}
