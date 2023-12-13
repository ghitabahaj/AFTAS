package com.youcode.aftas.mapper;

import com.youcode.aftas.DTO.FishDTO.response.FishResDto;
import com.youcode.aftas.entities.Fish;

public class FishMapper {
    public static FishResDto mapToDto(Fish fish){
        return FishResDto.builder()
                .id(fish.getId())
                .name(fish.getName())
                .averageWeight(fish.getAverageWeight())
                .level(fish.getLevel())
                .build();
    }
}
