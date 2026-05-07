package com.example.springboot_proj.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "missions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le véhicule est obligatoire")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicule_id", nullable = false)
    private Vehicule vehicule;

    @NotNull(message = "Le chauffeur est obligatoire")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chauffeur_id", nullable = false)
    private Chauffeur chauffeur;

    @NotBlank(message = "Le point de départ est obligatoire")
    @Column(name = "point_depart", nullable = false, length = 150)
    private String pointDepart;

    @NotBlank(message = "La destination est obligatoire")
    @Column(nullable = false, length = 150)
    private String destination;

    @Positive(message = "La distance doit être positive")
    @Column(nullable = false)
    private Double distance;
}

