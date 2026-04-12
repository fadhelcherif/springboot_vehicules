package com.example.springboot_proj.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ConsommationResponse(
        Long id,
        Long vehiculeId,
        String vehiculeImmatriculation,
        LocalDate date,
        BigDecimal quantiteCarburant,
        BigDecimal coutTotal
) {
}

