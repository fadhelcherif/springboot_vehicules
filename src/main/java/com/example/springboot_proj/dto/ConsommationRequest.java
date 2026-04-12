package com.example.springboot_proj.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ConsommationRequest(
        Long vehiculeId,
        LocalDate date,
        BigDecimal quantiteCarburant,
        BigDecimal coutTotal
) {
}

