package com.youcode.aftas.DTO.FishDTO.response;

import com.youcode.aftas.entities.Level;
import lombok.Builder;

@Builder
public record FishResDto(
        Long id,
        String name,
        Double averageWeight,
        Level level

) {

}
