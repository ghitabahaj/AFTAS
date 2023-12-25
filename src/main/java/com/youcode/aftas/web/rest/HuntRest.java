package com.youcode.aftas.web.rest;


import com.youcode.aftas.DTO.huntingDTO.huntingReqDTO;
import com.youcode.aftas.DTO.huntingDTO.huntingResDTO;
import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.entities.Hunting;
import com.youcode.aftas.mapper.HuntingMapper;
import com.youcode.aftas.service.CompetitionService;
import com.youcode.aftas.service.FishService;
import com.youcode.aftas.service.HuntingService;
import com.youcode.aftas.service.MemberService;
import com.youcode.aftas.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hunting")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class HuntRest {

    private  final HuntingService huntingService;
    private  final MemberService memberService;
    private  final CompetitionService competitionService;
    private  final FishService fishService;

    @PostMapping("/addHunting")
    public ResponseEntity<Response <huntingResDTO>> create(@RequestBody huntingReqDTO reqDto){

        Response <huntingResDTO> response = new Response<>();

        Hunting hunting = huntingService.addHunting(reqDto);
        response.setMessage("Hunting successfully");
        response.setResult(HuntingMapper.mapToDto(hunting));
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


}
