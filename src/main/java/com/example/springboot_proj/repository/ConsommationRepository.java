package com.example.springboot_proj.repository;

import com.example.springboot_proj.model.Consommation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsommationRepository extends JpaRepository<Consommation, Long> {

    interface FuelCostProjection {
        Long getVehiculeId();

        String getImmatriculation();

        java.math.BigDecimal getCoutTotalCarburant();
    }

    @Query("""
            select
                v.id as vehiculeId,
                v.immatriculation as immatriculation,
                coalesce(sum(c.coutTotal), 0) as coutTotalCarburant
            from Consommation c
            join c.vehicule v
            group by v.id, v.immatriculation
            order by coalesce(sum(c.coutTotal), 0) desc
            """)
    List<FuelCostProjection> getFuelCostDashboard();
}

