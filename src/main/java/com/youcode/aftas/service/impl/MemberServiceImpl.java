package com.youcode.aftas.service.impl;

import com.youcode.aftas.entities.*;
import com.youcode.aftas.repository.CompetitionRepository;
import com.youcode.aftas.repository.HuntingRepository;
import com.youcode.aftas.repository.MemberRepository;
import com.youcode.aftas.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MemberServiceImpl implements MemberService {


    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private final CompetitionRepository competitionRepository;


    public MemberServiceImpl(MemberRepository memberRepository, CompetitionRepository competitionRepository
                            ) {
        this.memberRepository = memberRepository;
        this.competitionRepository = competitionRepository;

    }
    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member addMember(Member member) {

        if (memberRepository.existsByIdentityNumber(member.getIdentityNumber())) {
            throw new IllegalArgumentException("Member with the same identityNumber already exists");
        }

        return memberRepository.save(member);
    }

    @Override
    public List<Member> searchMember(String keySearch) {

            return this.memberRepository.findByIdentityNumberOrNameOrFamilyName(keySearch, keySearch, keySearch);

    }

    @Override
    public Member updateMember(Member member, Long id) {
        Member existingMember = getMemberById(id);
        existingMember.setName(member.getName());
        existingMember.setFamilyName(member.getFamilyName());
        existingMember.setAccessDate(member.getAccessDate());
        existingMember.setNationality(member.getNationality());
        existingMember.setIdentityDocumentType(member.getIdentityDocumentType());
        existingMember.setIdentityNumber(member.getIdentityNumber());
        return memberRepository.save(existingMember);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public void registerInCompetition(Member member, Competition competition) {

        Optional<Member> existingMember = memberRepository.findByIdentityNumber(member.getIdentityNumber());
        if (existingMember.isEmpty()) {
            throw new EntityNotFoundException("Member not found with identityNumber: " + member.getIdentityNumber());
        }

        if (isRegistrationAllowed(member, competition)) {

            Ranking ranking = new Ranking();

            ranking.setRank(0);
            ranking.setScore(0);
            ranking.setCompetition(competition);
            ranking.setMember(member);

            if (member.getRankings() == null) {
                member.setRankings(new ArrayList<>());
            }
            member.getRankings().add(ranking);

            memberRepository.save(member);

            int currentNumberOfParticipants = competition.getNumberOfParticipants();
            competition.setNumberOfParticipants(currentNumberOfParticipants + 1);
            competitionRepository.save(competition);
        } else {
            throw new IllegalArgumentException("Registration is not allowed for this competition.");
        }
    }

    private boolean isRegistrationAllowed(Member member, Competition competition) {
        LocalTime registrationDeadline = competition.getStartTime().minusHours(24);
        LocalTime currentDateTime = LocalTime.now();
        return currentDateTime.isBefore(registrationDeadline);
    }


}
