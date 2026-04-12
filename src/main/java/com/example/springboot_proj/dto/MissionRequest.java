package com.example.springboot_proj.dto;

public record MissionRequest(
        Long vehiculeId,
        Long chauffeurId,
        String pointDepart,
        String destination,
        Double distance
) {
}

