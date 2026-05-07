package com.example.springboot_proj.converter;

import com.example.springboot_proj.dto.ChauffeurDTO;
import com.example.springboot_proj.entity.Chauffeur;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChauffeurConverter {

    // Entity → DTO
    public ChauffeurDTO toDto(Chauffeur chauffeur) {
        if (chauffeur == null) return null;
        ChauffeurDTO dto = new ChauffeurDTO();
        dto.setId(chauffeur.getId());
        dto.setNom(chauffeur.getNom());
        dto.setPermis(chauffeur.getPermis());
        dto.setExperience(chauffeur.getExperience());
        dto.setImageData(chauffeur.getImageData());
        return dto;
    }

    // DTO → Entity
    public Chauffeur fromDto(ChauffeurDTO dto) {
        if (dto == null) return null;
        Chauffeur c = new Chauffeur();
        c.setId(dto.getId());
        c.setNom(dto.getNom());
        c.setPermis(dto.getPermis());
        c.setExperience(dto.getExperience());
        c.setImageData(dto.getImageData());
        return c;
    }

    // List of entities → List of DTOs
    public List<ChauffeurDTO> toDtoList(List<Chauffeur> list) {
        return list.stream()
                   .map(this::toDto)
                   .collect(Collectors.toList());
    }
}

