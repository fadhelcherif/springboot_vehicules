package com.example.springboot_proj.dto;

public record AffectationRequest(
        Long vehiculeId,
        Long chauffeurId
) {
}

