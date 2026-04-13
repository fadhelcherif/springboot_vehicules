package com.example.springboot_proj.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Payload de creation ou mise a jour d'une consommation carburant")
public record ConsommationRequest(
        @Schema(description = "Identifiant du vehicule", example = "1")
        Long vehiculeId,
        @Schema(description = "Date de consommation", example = "2026-04-12")
        LocalDate date,
        @Schema(description = "Quantite de carburant (litres)", example = "42.70")
        BigDecimal quantiteCarburant,
        @Schema(description = "Cout total en devise locale", example = "145.90")
        BigDecimal coutTotal
) {
}

