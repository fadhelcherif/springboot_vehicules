package com.example.springboot_proj.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chauffeurs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chauffeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(nullable = false, length = 100)
    private String nom;

    @NotBlank(message = "Le permis est obligatoire")
    @Column(nullable = false, unique = true, length = 50)
    private String permis;

    @Min(value = 0, message = "L'expérience ne peut pas être négative")
    @Column(nullable = false)
    private Integer experience;

    @Column(columnDefinition = "LONGTEXT")
    private String imageData;

    @OneToMany(mappedBy = "chauffeur", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Mission> missions = new ArrayList<>();
}

