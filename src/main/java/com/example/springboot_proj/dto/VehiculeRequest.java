package com.example.springboot_proj.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload de creation ou mise a jour d'un vehicule")
public record VehiculeRequest(
        @Schema(description = "Immatriculation unique du vehicule", example = "123-TN-456")
        String immatriculation,
        @Schema(description = "Modele du vehicule", example = "Toyota Hilux")
        String modele,
        @Schema(description = "Type de vehicule", example = "Pickup")
        String type,
        @Schema(description = "Kilometrage actuel", example = "125000")
        Long kilometrage,
        @Schema(description = "Statut operationnel", example = "EN_SERVICE")
        String statut,
        @Schema(description = "Image du vehicule en format Base64", example = "data:image/jpeg;base64,...")
        String imageData
) {
}

