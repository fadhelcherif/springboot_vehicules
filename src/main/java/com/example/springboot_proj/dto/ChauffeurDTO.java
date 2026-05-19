package com.example.springboot_proj.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChauffeurDTO {

    @Schema(description = "Identifiant du chauffeur", example = "1")
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Schema(description = "Nom du chauffeur", example = "Ahmed Ben Salah")
    private String nom;

    @NotBlank(message = "Le permis est obligatoire")
    @Schema(description = "Numéro de permis", example = "B-987654")
    private String permis;

    @Min(value = 0, message = "L'expérience ne peut pas être négative")
    @Schema(description = "Années d'expérience", example = "6")
    private Integer experience;

    @Schema(description = "Image encodée en Base64 (data URI)", example = "data:image/jpeg;base64,...")
    private String imageData;
}

