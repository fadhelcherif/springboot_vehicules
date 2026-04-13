package com.example.springboot_proj.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Format standard des erreurs API")
public record ApiErrorResponse(
        @Schema(description = "Horodatage de l'erreur", example = "2026-04-12T12:10:00")
        LocalDateTime timestamp,
        @Schema(description = "Code HTTP", example = "404")
        int status,
        @Schema(description = "Libelle HTTP", example = "Not Found")
        String error,
        @Schema(description = "Message detaille", example = "Vehicule introuvable avec id=99")
        String message
) {
}

