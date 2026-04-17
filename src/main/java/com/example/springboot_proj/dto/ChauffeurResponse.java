package com.example.springboot_proj.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Reponse detaillee d'un chauffeur")
public record ChauffeurResponse(
        @Schema(description = "Identifiant technique", example = "1")
        Long id,
        @Schema(description = "Nom complet", example = "Ahmed Ben Salah")
        String nom,
        @Schema(description = "Numero de permis", example = "B-987654")
        String permis,
        @Schema(description = "Experience en annees", example = "6")
        Integer experience,
        @Schema(description = "Image du chauffeur en format Base64")
        String imageData
) {
}

