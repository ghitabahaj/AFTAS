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

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Family name cannot be null")
    @Size(min = 2, max = 50, message = "Family name must be between 2 and 50 characters")
    private String familyName;

    @NotNull(message = "Access date cannot be null")
    @PastOrPresent(message = "Access date must be in the past or present")
    @Temporal(TemporalType.DATE)
    private LocalDate accessDate;

    @NotNull(message = "nationality cannot be null")
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;

    @NotNull(message = "Identity number cannot be null")
    @Size(min = 2, max = 50, message = "Identity number must be between 2 and 50 characters")
    @Column(unique = true)
    private String identityNumber;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Hunting> huntings;

}
