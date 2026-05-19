package com.example.springboot_proj.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsommationDTO {

    @Schema(description = "Identifiant de la consommation", example = "1")
    private Long id;

    @NotNull(message = "L'ID du véhicule est obligatoire")
    @Schema(description = "Identifiant du véhicule", example = "1")
    private Long vehiculeId;

    @NotNull(message = "La date est obligatoire")
    @Schema(description = "Date du plein", example = "2026-05-19")
    private LocalDate date;

    @Positive(message = "La quantité doit être positive")
    @Schema(description = "Quantité de carburant (litres)", example = "42.7")
    private BigDecimal quantiteCarburant;

    @Positive(message = "Le coût doit être positif")
    @Schema(description = "Coût total du plein", example = "120.50")
    private BigDecimal coutTotal;
}

