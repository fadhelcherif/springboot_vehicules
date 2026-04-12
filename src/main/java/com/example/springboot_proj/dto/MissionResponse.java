package com.example.springboot_proj.dto;

public record MissionResponse(
        Long id,
        Long vehiculeId,
        String vehiculeImmatriculation,
        Long chauffeurId,
        String chauffeurNom,
        String pointDepart,
        String destination,
        Double distance
) {
}

