package com.example.springboot_proj.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeDTO {

    private Long id;

    @NotBlank(message = "L'immatriculation est obligatoire")
    private String immatriculation;

    @NotBlank(message = "Le modèle est obligatoire")
    @Size(min = 2, max = 100)
    private String modele;

    @NotBlank(message = "Le type est obligatoire")
    private String type;

    @Min(value = 0, message = "Le kilométrage ne peut pas être négatif")
    private Long kilometrage;

    @NotBlank(message = "Le statut est obligatoire")
    private String statut;

    private String imageData;
}

