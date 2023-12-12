package com.youcode.aftas.web.rest;


import com.youcode.aftas.service.FishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fishes")
@RequiredArgsConstructor
public class FishRest {
    private final FishService fishService;
}
