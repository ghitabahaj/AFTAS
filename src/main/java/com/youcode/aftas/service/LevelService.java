package com.youcode.aftas.service;


import com.youcode.aftas.DTO.LevelDTO;
import com.youcode.aftas.entities.Level;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LevelService {

    Level addLevel(LevelDTO level);
    List<Level> getAllLevel();
}
