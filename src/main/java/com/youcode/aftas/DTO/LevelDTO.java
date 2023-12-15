package com.youcode.aftas.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LevelDTO(

        Long id,
        @NotNull(message = "Description is required")
        @NotBlank(message = "Description cannot be blank")
        String description,
        @NotNull(message = "Points is required")
        @Min(value = 50, message = "Points must be at least 50")
        int points
) {
}