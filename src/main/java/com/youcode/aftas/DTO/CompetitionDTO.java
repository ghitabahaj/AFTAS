package com.youcode.aftas.DTO;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record CompetitionDTO(
        Long id,
        String code,
        @NotNull(message = "Date is required")
        @Future(message = "Date must be in the future")
        LocalDate date,
        @NotNull(message = "Start time is required")
        @Future(message = "Start time must be in the future")
        LocalTime startTime,
        @NotNull(message = "End time is required")
        @Future(message = "end time must be in the future")
        LocalTime endTime,
        @NotNull(message = "Number of participants cannot be null")
        @Positive(message = "Number of participants must be positive")
        Integer numberOfParticipants,
        @NotNull(message = "Location cannot be null")
        @NotBlank(message = "Location cannot be blank")
        String location,
        @NotNull(message = "Amount cannot be null")
        @PositiveOrZero(message = "Amount must be positive")
        Double amount
) {
}