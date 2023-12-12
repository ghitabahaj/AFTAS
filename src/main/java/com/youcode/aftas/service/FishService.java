package com.youcode.aftas.service;


import com.youcode.aftas.entities.Fish;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FishService {

    Fish addFish(Fish fish);

    void removeFish(Fish fish);

    Fish getFish(String name);

    Fish getFishById(String id);

    Fish updateFish(Fish fish);

    List<Fish> findAll();
}
