package com.youcode.aftas.DTO.competitionDto;

import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Ranking;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record CompetitionDetailsDto(
        Long id,
        String code,
        LocalDate date,
        LocalTime startTime,

        LocalTime endTime,
        Integer numberOfParticipants,
        String location,
        Double amount,
        List<Ranking> rankingList
) {
    public static CompetitionDetailsDto toCompetitionDetailsDto(Competition competition){
        return new CompetitionDetailsDto(competition.getId(),competition.getCode()
                ,competition.getDate(),competition.getStartTime()
                ,competition.getEndTime(),
                competition.getNumberOfParticipants(),competition.getLocation(),
                competition.getAmount(),competition.getRankings());
    }
}
