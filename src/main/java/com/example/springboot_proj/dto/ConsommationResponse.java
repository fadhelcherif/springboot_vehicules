package com.example.springboot_proj.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Reponse detaillee d'une consommation carburant")
public record ConsommationResponse(
        @Schema(description = "Identifiant de la consommation", example = "1")
        Long id,
        @Schema(description = "Identifiant du vehicule", example = "1")
        Long vehiculeId,
        @Schema(description = "Immatriculation du vehicule", example = "123-TN-456")
        String vehiculeImmatriculation,
        @Schema(description = "Date de consommation", example = "2026-04-12")
        LocalDate date,
        @Schema(description = "Quantite de carburant", example = "42.70")
        BigDecimal quantiteCarburant,
        @Schema(description = "Cout total", example = "145.90")
        BigDecimal coutTotal
) {
}

