package com.example.springboot_proj.dto;

public record ChauffeurRequest(
        String nom,
        String permis,
        Integer experience
) {
}

