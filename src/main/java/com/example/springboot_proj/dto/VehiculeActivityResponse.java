package com.example.springboot_proj.dto;

public record VehiculeActivityResponse(
        Long vehiculeId,
        String immatriculation,
        Long nombreMissions,
        Double distanceTotale
) {
}

