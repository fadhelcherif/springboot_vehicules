package com.example.springboot_proj.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload de creation ou mise a jour d'une mission")
public record MissionRequest(
        @Schema(description = "Identifiant du vehicule affecte", example = "1")
        Long vehiculeId,
        @Schema(description = "Identifiant du chauffeur affecte", example = "2")
        Long chauffeurId,
        @Schema(description = "Point de depart", example = "Tunis")
        String pointDepart,
        @Schema(description = "Destination", example = "Sfax")
        String destination,
        @Schema(description = "Distance de la mission en km", example = "270.5")
        Double distance
) {
}

