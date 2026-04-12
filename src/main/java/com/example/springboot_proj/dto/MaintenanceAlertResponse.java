package com.example.springboot_proj.dto;

public record MaintenanceAlertResponse(
        Long vehiculeId,
        String immatriculation,
        String modele,
        Long kilometrage,
        Long seuilKm,
        Long depassementKm,
        String statut
) {
}

