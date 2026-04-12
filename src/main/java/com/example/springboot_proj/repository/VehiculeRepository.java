package com.example.springboot_proj.repository;

import com.example.springboot_proj.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    List<Vehicule> findByKilometrageGreaterThanEqualOrderByKilometrageDesc(Long kilometrage);
}

