package com.youcode.aftas.service.impl;


import com.youcode.aftas.DTO.LevelDTO;
import com.youcode.aftas.entities.Level;
import com.youcode.aftas.repository.LevelRepository;
import com.youcode.aftas.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Level addLevel(LevelDTO level) {
        Optional<Level> level1 = levelRepository.findLevelByPoints(level.points());
        if(level1.isPresent()){
            throw new IllegalArgumentException("This point is already exists");
        }
        
        Level addedLevel = Level.builder()
                .description(level.description())
                .points(level.points()).build();
                
        return levelRepository.save(addedLevel);

    }

    @Override
    public List<Level> getAllLevel() {
        return null;
    }
}
