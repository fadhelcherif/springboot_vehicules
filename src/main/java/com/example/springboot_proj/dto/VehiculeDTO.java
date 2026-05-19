package com.example.springboot_proj.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeDTO {

    @Schema(description = "Identifiant du véhicule", example = "1")
    private Long id;

    @NotBlank(message = "L'immatriculation est obligatoire")
    @Schema(description = "Immatriculation", example = "123-TN-456")
    private String immatriculation;

    @NotBlank(message = "Le modèle est obligatoire")
    @Size(min = 2, max = 100)
    @Schema(description = "Modèle du véhicule", example = "Toyota Hilux")
    private String modele;

    @NotBlank(message = "Le type est obligatoire")
    @Schema(description = "Type de véhicule", example = "Pickup")
    private String type;

    @Min(value = 0, message = "Le kilométrage ne peut pas être négatif")
    @Schema(description = "Kilométrage actuel", example = "125000")
    private Long kilometrage;

    @NotBlank(message = "Le statut est obligatoire")
    @Schema(description = "Statut du véhicule", example = "EN_SERVICE")
    private String statut;

    @Schema(description = "Image encodée en Base64 (data URI)", example = "data:image/jpeg;base64,...")
    private String imageData;
}

