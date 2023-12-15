package com.youcode.aftas.web.rest;


import com.youcode.aftas.DTO.RankingDTO;
import com.youcode.aftas.DTO.rankingDto.RankingRequestDto;
import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Member;
import com.youcode.aftas.service.CompetitionService;
import com.youcode.aftas.service.MemberService;
import com.youcode.aftas.service.RankingService;
import com.youcode.aftas.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rankings")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RankingRest {

    private final RankingService rankingService;
    private final MemberService memberService;
    private final CompetitionService competitionService;

    @PostMapping("/register")
    public ResponseEntity<Response<RankingDTO>> registerMember(@RequestBody RankingRequestDto rankingRequestDto) {
        Response<RankingDTO> response = new Response<>();
        Member member = memberService.getByIdentityNumber(rankingRequestDto.identityNumber());
        Competition competition = competitionService.findByCode(rankingRequestDto.code());
        memberService.registerInCompetition(member, competition);
        response.setMessage("Member registered  successfully");
        return  ResponseEntity.ok(response);
    }
}
