package com.youcode.aftas.mapper;

import com.youcode.aftas.DTO.RankingDTO;
import com.youcode.aftas.entities.Ranking;

public class RankingMapper {
    public static RankingDTO mapToDto(Ranking ranking){
        return RankingDTO.builder()
                .rank(ranking.getRank())
                .score(ranking.getScore())
                .competition(ranking.getCompetition())
                .member(ranking.getMember())
                .build();
    }
}
