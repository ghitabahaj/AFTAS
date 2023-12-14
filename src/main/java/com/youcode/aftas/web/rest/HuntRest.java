package com.youcode.aftas.web.rest;


import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.entities.Hunting;
import com.youcode.aftas.service.CompetitionService;
import com.youcode.aftas.service.FishService;
import com.youcode.aftas.service.HuntingService;
import com.youcode.aftas.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hunting")
@RequiredArgsConstructor
public class HuntRest {

    private  final HuntingService huntingService;
    private  final MemberService memberService;
    private  final CompetitionService competitionService;
    private  final FishService fishService;

    @PostMapping("/addHunting")
    public ResponseEntity<?> create(@RequestParam Long MemberId ,@RequestParam Long FishId  ,@RequestParam Long CompetitionId ,@RequestParam Double Weight){
        Hunting hunting = new Hunting();
        hunting.setMember(memberService.getMemberById(MemberId));
        hunting.setFish(fishService.getFishById(FishId));
        hunting.setCompetition(competitionService.findById(CompetitionId));
        Hunting hunt = huntingService.addHunting(hunting,Weight);
        return new ResponseEntity<>(hunt, HttpStatus.OK);

    }


}
