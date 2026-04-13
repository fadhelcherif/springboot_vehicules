package com.example.springboot_proj.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Alerte de maintenance preventive basee sur le kilometrage")
public record MaintenanceAlertResponse(
        @Schema(description = "Identifiant du vehicule", example = "1")
        Long vehiculeId,
        @Schema(description = "Immatriculation", example = "123-TN-456")
        String immatriculation,
        @Schema(description = "Modele", example = "Toyota Hilux")
        String modele,
        @Schema(description = "Kilometrage actuel", example = "125000")
        Long kilometrage,
        @Schema(description = "Seuil de maintenance utilise", example = "10000")
        Long seuilKm,
        @Schema(description = "Km depasses au-dessus du seuil", example = "115000")
        Long depassementKm,
        @Schema(description = "Statut du vehicule", example = "EN_SERVICE")
        String statut
) {
}

