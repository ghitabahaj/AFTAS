package com.youcode.aftas.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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


    @Column(unique = true)
    private String code;


    private LocalDate date;

    private LocalTime startTime;


    private LocalTime endTime;


    private int numberOfParticipants;

    @NotBlank(message = "Location is required")
    private String location;

    private Double amount;

    @OneToMany(mappedBy = "competition")
    @JsonIgnore
    private List<Ranking> rankings;


}
