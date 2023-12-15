package com.youcode.aftas.embedded;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankPrimaryId implements Serializable {

    @Column(name = "competition_id")
    private Long competitionId;

    @Column(name = "member_id")
    private Long memberId;

}
