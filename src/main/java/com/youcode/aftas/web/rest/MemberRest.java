package com.youcode.aftas.web.rest;

import com.youcode.aftas.entities.Member;
import com.youcode.aftas.handler.response.ResponseMessage;
import com.youcode.aftas.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberRest{

    private final MemberService memberService;

    public MemberRest(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getMemberById(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        if(member == null) {
            return ResponseMessage.notFound("Member not found");
        }else {
            return ResponseMessage.ok( "Success", member);
        }
    }

    @PostMapping
    public ResponseEntity<?>  addMember(@Valid @RequestBody Member member) {
        Member savedmember = memberService.addMember(member);
        if(savedmember == null) {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }else {
            return ResponseEntity.ok(savedmember);
        }
    }

    @GetMapping
    public ResponseEntity<?>  searchMember(@RequestBody String name) {
        List<Member> members = memberService.searchMember(name);
        if(members.isEmpty()) {
            return ResponseMessage.notFound("Member not found");
        }else {
            return ResponseMessage.ok("Success", members);
        }
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

}