package com.youcode.aftas.embedded;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RankPrimaryId implements Serializable {

    @Column(name = "competition_id")
    private Long competitionId;

    @Column(name = "member_id")
    private Long memberId;

}
