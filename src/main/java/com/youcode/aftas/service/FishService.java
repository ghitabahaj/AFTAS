package com.youcode.aftas.service;


import com.youcode.aftas.DTO.FishDTO.FishDTO;
import com.youcode.aftas.DTO.FishDTO.requests.FishReqDto;
import com.youcode.aftas.entities.Fish;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FishService {

    Fish addFish(FishReqDto fish);

    void removeFish(Fish fish);

    Fish getFish(String name);

    Fish getFishById(Long id);

    Fish updateFish(Fish fish);

    List<Fish> findAll();
}
