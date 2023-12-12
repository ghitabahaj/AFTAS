package com.youcode.aftas.entities;


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

    @NotBlank(message = "Code is required")
    @Pattern(regexp = "^ims-\\d{2}-\\d{2}-\\d{2}$", message = "Invalid competition code format")
    private String code;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    private LocalDateTime endTime;

    @Min(value = 1, message = "Number of participants must be at least 1")
    private int numberOfParticipants;

    @NotBlank(message = "Location is required")
    private String location;

    private int amountOfFish;

    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;

}
