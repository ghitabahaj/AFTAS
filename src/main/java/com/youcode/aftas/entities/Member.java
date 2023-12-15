package com.youcode.aftas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.aftas.enums.IdentityDocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private String name;


    private String familyName;


    @Temporal(TemporalType.DATE)
    private LocalDate accessDate;


    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;


    @Column(unique = true)
    private String identityNumber;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Hunting> huntings;

}
