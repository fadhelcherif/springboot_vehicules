package com.example.springboot_proj.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Indicateur d'utilisation d'un vehicule")
public record VehiculeActivityResponse(
        @Schema(description = "Identifiant du vehicule", example = "1")
        Long vehiculeId,
        @Schema(description = "Immatriculation", example = "123-TN-456")
        String immatriculation,
        @Schema(description = "Nombre total de missions", example = "18")
        Long nombreMissions,
        @Schema(description = "Distance totale parcourue", example = "5420.75")
        Double distanceTotale
) {
}

