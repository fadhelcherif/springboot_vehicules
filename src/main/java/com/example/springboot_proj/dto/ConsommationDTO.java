package com.example.springboot_proj.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsommationDTO {

    private Long id;

    @NotNull(message = "L'ID du véhicule est obligatoire")
    private Long vehiculeId;

    @NotNull(message = "La date est obligatoire")
    private LocalDate date;

    @Positive(message = "La quantité doit être positive")
    private BigDecimal quantiteCarburant;

    @Positive(message = "Le coût doit être positif")
    private BigDecimal coutTotal;
}

