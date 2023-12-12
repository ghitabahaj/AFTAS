package com.youcode.aftas.service.impl;


import com.youcode.aftas.entities.Level;
import com.youcode.aftas.repository.LevelRepository;
import com.youcode.aftas.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Level addLevel(Level level) {
        return levelRepository.save(level);
    }
}
