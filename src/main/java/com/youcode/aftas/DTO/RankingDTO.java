package com.youcode.aftas.DTO;

import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Member;
import lombok.Builder;

@Builder
public record RankingDTO (

    Integer score,
    Integer rank,
    Member member,
    Competition competition
){
}

