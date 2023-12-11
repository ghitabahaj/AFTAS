package com.youcode.aftas.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate date;

    private Time startTime;

    private Time endTime;

    private int numberOfParticipants;

    private String location;

    private int amountOfFish;

    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;

}
