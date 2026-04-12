package com.example.springboot_proj.dto;

import java.math.BigDecimal;

public record FuelCostByVehiculeResponse(
        Long vehiculeId,
        String immatriculation,
        BigDecimal coutTotalCarburant
) {
}

