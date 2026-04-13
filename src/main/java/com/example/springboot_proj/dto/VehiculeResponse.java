package com.example.springboot_proj.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Reponse detaillee d'un vehicule")
public record VehiculeResponse(
        @Schema(description = "Identifiant technique", example = "1")
        Long id,
        @Schema(description = "Immatriculation unique", example = "123-TN-456")
        String immatriculation,
        @Schema(description = "Modele", example = "Toyota Hilux")
        String modele,
        @Schema(description = "Type", example = "Pickup")
        String type,
        @Schema(description = "Kilometrage", example = "125000")
        Long kilometrage,
        @Schema(description = "Statut", example = "EN_SERVICE")
        String statut
) {
}

