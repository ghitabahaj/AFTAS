package com.youcode.aftas.DTO.MemberDTO;

import com.youcode.aftas.enums.IdentityDocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record MemberDTO(
        Long id,

        @NotNull(message = "Name cannot be null")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,
        @NotNull(message = "Family name cannot be null")
        @Size(min = 2, max = 50, message = "Family name must be between 2 and 50 characters")
        String familyName,

        @NotNull(message = "Access date cannot be null")
        @PastOrPresent(message = "Access date must be in the past or present")
        LocalDate accessDate,
        @NotNull(message = "Nationality is required")
        @NotBlank(message = "Nationality cannot be blank")
        String nationality,
        @NotNull(message = "nationality cannot be null")
        IdentityDocumentType identityDocumentType,
        @NotNull(message = "Identity number cannot be null")
        @Size(min = 2, max = 50, message = "Identity number must be between 2 and 50 characters")
        String identityNumber
) {
}
