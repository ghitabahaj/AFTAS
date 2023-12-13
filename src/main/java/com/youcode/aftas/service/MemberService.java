package com.youcode.aftas.service;

import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Member;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface MemberService {

    Member getMemberById(Long id);
    Member addMember(Member member);
    List<Member> searchMember(String keySearch);
    Member updateMember(Member member, Long id);
    void deleteMember(Long id);
    void registerInCompetition(Member member, Competition competition);


}
