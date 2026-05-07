package com.example.springboot_proj.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChauffeurDTO {

    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le permis est obligatoire")
    private String permis;

    @Min(value = 0, message = "L'expérience ne peut pas être négative")
    private Integer experience;

    private String imageData;
}

