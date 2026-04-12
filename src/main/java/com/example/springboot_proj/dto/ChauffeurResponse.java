package com.example.springboot_proj.dto;

public record ChauffeurResponse(
        Long id,
        String nom,
        String permis,
        Integer experience
) {
}

