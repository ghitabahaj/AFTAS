package com.youcode.aftas.entities;

import com.youcode.aftas.embedded.RankPrimaryId;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ranking {

    @EmbeddedId
    private RankPrimaryId rankPrimaryId;

    private int rank;

    private int score;
    @MapsId("memberId")
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @MapsId("competitionId")
    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;
}