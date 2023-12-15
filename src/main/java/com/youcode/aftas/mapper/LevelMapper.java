package com.youcode.aftas.mapper;

import com.youcode.aftas.DTO.LevelDTO;
import com.youcode.aftas.entities.Level;


public class LevelMapper {
    public static LevelDTO mapToDto(Level level){
          return LevelDTO.builder()
                  .id(level.getId())
                  .description(level.getDescription())
                  .points(level.getPoints())
                  .build();
    }
}
