package com.youcode.aftas.mapper;

import com.youcode.aftas.DTO.MemberDTO.MemberDTO;
import com.youcode.aftas.entities.Member;

public class MemberMapper {
    public static MemberDTO mapToDto(Member member){
        return MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .familyName(member.getFamilyName())
                .accessDate(member.getAccessDate())
                .nationality(member.getNationality())
                .identityDocumentType(member.getIdentityDocumentType())
                .identityNumber(member.getIdentityNumber())
                .build();
    }
}
