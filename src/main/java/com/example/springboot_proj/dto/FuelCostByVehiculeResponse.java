package com.example.springboot_proj.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Indicateur de cout carburant par vehicule")
public record FuelCostByVehiculeResponse(
        @Schema(description = "Identifiant du vehicule", example = "1")
        Long vehiculeId,
        @Schema(description = "Immatriculation", example = "123-TN-456")
        String immatriculation,
        @Schema(description = "Somme des couts carburant", example = "1250.40")
        BigDecimal coutTotalCarburant
) {
}

