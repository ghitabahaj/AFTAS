package com.youcode.aftas.service.impl;


import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.repository.FishRepository;
import com.youcode.aftas.service.FishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FishServiceImpl implements FishService {

    final private  FishRepository fishRepository;

    @Override
    public Fish addFish(Fish fish) {
        return null;
    }

    @Override
    public void removeFish(Fish fish) {

    }

    @Override
    public Fish getFish(String name) {
        return null;
    }

    @Override
    public Fish getFishById(String id) {
        return null;
    }

    @Override
    public Fish updateFish(Fish fish) {
        return null;
    }

    @Override
    public List<Fish> findAll() {
        return null;
    }
}
