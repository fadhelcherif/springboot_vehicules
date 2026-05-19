package com.example.springboot_proj.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionDTO {

    @Schema(description = "Identifiant de la mission", example = "1")
    private Long id;

    @NotNull(message = "L'ID du véhicule est obligatoire")
    @Schema(description = "Identifiant du véhicule", example = "1")
    private Long vehiculeId;

    @NotNull(message = "L'ID du chauffeur est obligatoire")
    @Schema(description = "Identifiant du chauffeur", example = "1")
    private Long chauffeurId;

    @NotBlank(message = "Le point de départ est obligatoire")
    @Schema(description = "Point de départ", example = "Dakar Centre")
    private String pointDepart;

    @NotBlank(message = "La destination est obligatoire")
    @Schema(description = "Destination", example = "Bamako")
    private String destination;

    @Positive(message = "La distance doit être positive")
    @Schema(description = "Distance en kilomètres", example = "250.5")
    private Double distance;
}

