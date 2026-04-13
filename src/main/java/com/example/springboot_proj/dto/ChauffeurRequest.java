package com.example.springboot_proj.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload de creation ou mise a jour d'un chauffeur")
public record ChauffeurRequest(
        @Schema(description = "Nom complet du chauffeur", example = "Ahmed Ben Salah")
        String nom,
        @Schema(description = "Numero de permis", example = "B-987654")
        String permis,
        @Schema(description = "Nombre d'annees d'experience", example = "6")
        Integer experience
) {
}

