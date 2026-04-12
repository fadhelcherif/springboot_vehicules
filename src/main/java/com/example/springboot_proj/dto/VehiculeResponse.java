package com.example.springboot_proj.dto;

public record VehiculeResponse(
        Long id,
        String immatriculation,
        String modele,
        String type,
        Long kilometrage,
        String statut
) {
}

