package com.youcode.aftas.web.rest;


import com.youcode.aftas.DTO.LevelDTO;
import com.youcode.aftas.entities.Level;
import com.youcode.aftas.mapper.LevelMapper;
import com.youcode.aftas.service.LevelService;
import com.youcode.aftas.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/level")
@RequiredArgsConstructor
public class LevelRest {

    private final LevelService levelService;

    @PostMapping("/create")
    public ResponseEntity<Response<LevelDTO>> saveLevel(@Valid @RequestBody LevelDTO levelDto){
        Response<LevelDTO> response = new Response<>();
        Level level = levelService.addLevel(levelDto);
        response.setResult(LevelMapper.mapToDto(level));
        response.setMessage("Created Level Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
