package com.youcode.aftas.web.rest;


import com.youcode.aftas.DTO.FishDTO.requests.FishReqDto;
import com.youcode.aftas.DTO.FishDTO.response.FishResDto;
import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.mapper.FishMapper;
import com.youcode.aftas.service.FishService;
import com.youcode.aftas.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fishes")
@RequiredArgsConstructor
public class FishRest {
    private final FishService fishService;
    @GetMapping
    public ResponseEntity<Response<List<FishResDto>>> getAllFish(){
        Response<List<FishResDto>> response = new Response<>();
//        List<Fish> fish = fishService.getAllFish();
//        response.setResult(fish.stream()
//                .map(FishMapper::mapToDto)
//                .toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Response<FishResDto>> getFishByName(@Valid @PathVariable("name") String name){
        Response<FishResDto> response = new Response<>();
////        Fish fish = fishService.getFishByName(name);
//        response.setResult(FishMapper.mapToDto(fish));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Response<FishResDto>> saveFish(@Valid @RequestBody FishReqDto fishDto){
        Response<FishResDto> response = new Response<>();
        Fish fish = fishService.addFish(fishDto);
        response.setResult(FishMapper.mapToDto(fish));
        response.setMessage("Created Fish Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
