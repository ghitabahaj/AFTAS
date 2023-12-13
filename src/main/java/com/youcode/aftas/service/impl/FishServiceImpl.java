package com.youcode.aftas.service.impl;


import com.youcode.aftas.DTO.FishDTO.FishDTO;
import com.youcode.aftas.DTO.FishDTO.requests.FishReqDto;
import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.entities.Level;
import com.youcode.aftas.handler.exception.AlreadyExistsException;
import com.youcode.aftas.repository.FishRepository;
import com.youcode.aftas.repository.LevelRepository;
import com.youcode.aftas.service.FishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FishServiceImpl implements FishService {

    private final  FishRepository fishRepository;
    private final LevelRepository levelRepository;

    @Override
    public Fish addFish(FishReqDto fish) {
        if (fish.name() == null || fish.name().trim().isEmpty()) {
            throw new IllegalArgumentException("Fish name cannot be null or empty");
        }

        if (fish.averageWeight() == 0){
            throw new IllegalArgumentException("Fish average weight cannot be 0");
        }

        if (fishRepository.existsByName(fish.name())) {
            throw new AlreadyExistsException("Fish with name " + fish.name() + " already exists");
        }
        Optional<Level> level = Optional.ofNullable(levelRepository.findLevelById(fish.level())
                .orElseThrow(() -> new IllegalArgumentException("Sorry this level not exists")));
        Fish fish1 = Fish.builder()
                    .name(fish.name())
                    .level(level.get())
                    .averageWeight(fish.averageWeight())
                    .build();

        return fishRepository.save(fish1);
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
