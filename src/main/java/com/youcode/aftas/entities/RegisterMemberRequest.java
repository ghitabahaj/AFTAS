package com.youcode.aftas.entities;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterMemberRequest {
    private Member member;
    private Competition competition;
}
