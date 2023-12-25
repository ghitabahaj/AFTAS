package com.youcode.aftas.DTO.huntingDTO;

import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.entities.Member;
import lombok.Builder;


@Builder
public record huntingResDTO (

        Integer numberOfFishes,
        Competition competition,
        Fish fish,
        Member member
) {
}