package com.youcode.aftas.web.rest;

import com.youcode.aftas.DTO.MemberDTO.MemberDTO;
import com.youcode.aftas.entities.Member;
import com.youcode.aftas.handler.response.ResponseMessage;
import com.youcode.aftas.mapper.MemberMapper;
import com.youcode.aftas.service.MemberService;
import com.youcode.aftas.utils.Response;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@CrossOrigin(origins = "http://localhost:4200")
public class MemberRest{

    private final MemberService memberService;

    public MemberRest(MemberService memberService) {
        this.memberService = memberService;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MemberDTO>>  getMemberById(@PathVariable Long id) {
     Response<MemberDTO> response = new Response<>();
     Member member = memberService.getMemberById(id);
     response.setResult(MemberMapper.mapToDto(member));
     response.setMessage("Member retrieved successfully");
     return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addMember")
    public ResponseEntity<Response<MemberDTO>>  addMember(@Valid @RequestBody MemberDTO member) {
        Response<MemberDTO> response = new Response<>();
        Member member1 = memberService.addMember(member);
        response.setResult(MemberMapper.mapToDto(member1));
        response.setMessage("Created Member Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Response<List <MemberDTO>>>  searchMember(@PathVariable String name) {
        Response<List <MemberDTO>> response = new Response<>();
         List<Member> members = memberService.searchMember(name);
        response.setResult(members.stream()
                .map(MemberMapper::mapToDto)
                .toList());
        response.setMessage( members.size() +" Members Found");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  updateMember(@RequestBody Member member, @PathVariable Long id) {
        Member member1 = memberService.updateMember(member, id);
        if(member1 == null) {
            return ResponseMessage.badRequest("Member not updated");
        }else {
            return ResponseMessage.created("Member updated successfully", member1);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Response <List<MemberDTO>>> getAll() {
        Response <List<MemberDTO>> response = new Response<>();
        List<Member> members = memberService.findAll();
        response.setResult(members.stream()
                .map(MemberMapper::mapToDto)
                .toList());
        response.setMessage("Members fetched successfully");

        return ResponseEntity.ok(response);
    }




}