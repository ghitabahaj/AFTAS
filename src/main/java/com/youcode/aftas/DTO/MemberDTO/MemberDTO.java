package com.youcode.aftas.DTO.MemberDTO;

import com.youcode.aftas.enums.IdentityDocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record MemberDTO(
        Long id,
        @NotNull(message = "Please provide a valid number")
        Integer num,
        @NotNull(message = "First name is required")
        @NotBlank(message = "First name cannot be blank")
        String firstName,
        @NotNull(message = "Last name is required")
        @NotBlank(message = "Last name cannot be blank")
        String lastName,
        LocalDate accessionDate,
        @NotNull(message = "Nationality is required")
        @NotBlank(message = "Nationality cannot be blank")
        String nationality,
        @NotNull(message = "Please select an identity type")
        IdentityDocumentType identityDocumentType,
        @NotNull(message = "Identity number is required")
        @NotBlank(message = "Identity number cannot be blank")
        String identityNumber
) {
}
