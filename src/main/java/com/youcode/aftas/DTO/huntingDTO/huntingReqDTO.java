package com.youcode.aftas.DTO.huntingDTO;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record huntingReqDTO(
        @NotNull(message = "Member identity number cannot be null")
        String IdentityNumber,
        @NotNull(message = "Competition code cannot be null")
        String competitionCode,
        @NotBlank(message = "Please provide a name")
        String name,
        @NotNull(message = "Weight is required")
        @DecimalMin(value = "0.10", message = "Weight should be greater than 0.10")
        Double weight
) {
}
