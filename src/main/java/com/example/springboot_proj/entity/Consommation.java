package com.example.springboot_proj.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "consommations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consommation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le véhicule est obligatoire")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicule_id", nullable = false)
    private Vehicule vehicule;

    @NotNull(message = "La date est obligatoire")
    @Column(nullable = false)
    private LocalDate date;

    @Positive(message = "La quantité doit être positive")
    @Column(name = "quantite_carburant", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantiteCarburant;

    @Positive(message = "Le coût doit être positif")
    @Column(name = "cout_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal coutTotal;
}

