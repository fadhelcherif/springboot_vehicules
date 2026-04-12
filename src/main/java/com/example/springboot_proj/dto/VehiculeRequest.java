package com.example.springboot_proj.dto;

public record VehiculeRequest(
        String immatriculation,
        String modele,
        String type,
        Long kilometrage,
        String statut
) {
}

