package com.example.springboot_proj.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionDTO {

    private Long id;

    @NotNull(message = "L'ID du véhicule est obligatoire")
    private Long vehiculeId;

    @NotNull(message = "L'ID du chauffeur est obligatoire")
    private Long chauffeurId;

    @NotBlank(message = "Le point de départ est obligatoire")
    private String pointDepart;

    @NotBlank(message = "La destination est obligatoire")
    private String destination;

    @Positive(message = "La distance doit être positive")
    private Double distance;
}

