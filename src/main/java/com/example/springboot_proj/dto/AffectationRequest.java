package com.example.springboot_proj.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload de reassignment chauffeur-vehicule pour une mission")
public record AffectationRequest(
        @Schema(description = "Identifiant du nouveau vehicule", example = "3")
        Long vehiculeId,
        @Schema(description = "Identifiant du nouveau chauffeur", example = "5")
        Long chauffeurId
) {
}

