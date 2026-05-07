package com.example.springboot_proj.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "L'immatriculation est obligatoire")
    @Column(nullable = false, unique = true, length = 30)
    private String immatriculation;

    @NotBlank(message = "Le modèle est obligatoire")
    @Column(nullable = false, length = 100)
    private String modele;

    @NotBlank(message = "Le type est obligatoire")
    @Column(nullable = false, length = 50)
    private String type;

    @Min(value = 0, message = "Le kilométrage ne peut pas être négatif")
    @Column(nullable = false)
    private Long kilometrage;

    @NotBlank(message = "Le statut est obligatoire")
    @Column(nullable = false, length = 30)
    private String statut;

    @Column(columnDefinition = "LONGTEXT")
    private String imageData;

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Consommation> consommations = new ArrayList<>();
}

