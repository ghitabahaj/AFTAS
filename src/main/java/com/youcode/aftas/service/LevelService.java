package com.youcode.aftas.service;


import com.youcode.aftas.entities.Level;
import org.springframework.stereotype.Service;

@Service
public interface LevelService {

    Level addLevel(Level level);
}
