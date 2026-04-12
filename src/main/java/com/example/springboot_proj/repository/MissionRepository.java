package com.example.springboot_proj.repository;

import com.example.springboot_proj.dto.VehiculeActivityResponse;
import com.example.springboot_proj.model.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
            select new com.example.springboot_proj.dto.VehiculeActivityResponse(
                v.id,
                v.immatriculation,
                count(m.id),
                coalesce(sum(m.distance), 0)
            )
            from Mission m
            join m.vehicule v
            group by v.id, v.immatriculation
            order by count(m.id) desc, coalesce(sum(m.distance), 0) desc
            """)
    List<VehiculeActivityResponse> findVehiculeActivity(Pageable pageable);
}

