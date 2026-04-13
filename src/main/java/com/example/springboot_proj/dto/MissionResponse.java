package com.example.springboot_proj.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Reponse detaillee d'une mission")
public record MissionResponse(
        @Schema(description = "Identifiant de la mission", example = "1")
        Long id,
        @Schema(description = "Identifiant du vehicule", example = "1")
        Long vehiculeId,
        @Schema(description = "Immatriculation du vehicule", example = "123-TN-456")
        String vehiculeImmatriculation,
        @Schema(description = "Identifiant du chauffeur", example = "2")
        Long chauffeurId,
        @Schema(description = "Nom du chauffeur", example = "Ahmed Ben Salah")
        String chauffeurNom,
        @Schema(description = "Point de depart", example = "Tunis")
        String pointDepart,
        @Schema(description = "Destination", example = "Sfax")
        String destination,
        @Schema(description = "Distance en km", example = "270.5")
        Double distance
) {
}

