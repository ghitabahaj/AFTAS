package com.youcode.aftas.service.impl;


import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.handler.exception.AlreadyExistsException;
import com.youcode.aftas.repository.FishRepository;
import com.youcode.aftas.service.FishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FishServiceImpl implements FishService {

    final private  FishRepository fishRepository;

    @Override
    public Fish addFish(Fish fish) {
        if (fish.getName() == null || fish.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Fish name cannot be null or empty");
        }

        if (fish.getAverageWeight() == 0){
            throw new IllegalArgumentException("Fish average weight cannot be 0");
        }

        if (fishRepository.existsByName(fish.getName())) {
            throw new AlreadyExistsException("Fish with name " + fish.getName() + " already exists");
        }

        return fishRepository.save(fish);
    }

    @Override
    public void removeFish(Fish fish) {

        if (fishRepository.existsByName(fish.getName())){

            fishRepository.delete(fish);

        }else{

            throw new IllegalArgumentException("Fish not find by name " + fish.getName() + "cannot be removed");
        }

    }

    @Override
    public Fish getFish(String name) {

        if (fishRepository.existsByName(name)){

            return fishRepository.findByName(name);

        }else{

            throw new IllegalArgumentException("Fish not find by name " + name);
        }
    }

    @Override
    public Fish getFishById(Long id) {
        Optional<Fish> fish = fishRepository.findById(id);
        return fish.orElse(null);
    }

    @Override
    public Fish updateFish(Fish fish) {

        if (fishRepository.existsByName(fish.getName())) {

            Fish existingFish = fishRepository.findByName(fish.getName());

            if (existingFish != null && existingFish.getId().equals(fish.getId())) {

                return fishRepository.save(fish);

            } else {
                throw new IllegalArgumentException("Fish with name " + fish.getName() + " already exists with a different ID.");
            }
        } else {

            throw new IllegalArgumentException("Fish not found by name " + fish.getName());
        }
    }

    @Override
    public List<Fish> findAll() {

        return fishRepository.findAll();
    }
}
