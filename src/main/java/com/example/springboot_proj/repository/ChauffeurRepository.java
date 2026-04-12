package com.example.springboot_proj.repository;

import com.example.springboot_proj.model.Chauffeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChauffeurRepository extends JpaRepository<Chauffeur, Long> {
}

