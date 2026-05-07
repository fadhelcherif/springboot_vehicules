package com.example.springboot_proj.converter;

import com.example.springboot_proj.dto.VehiculeDTO;
import com.example.springboot_proj.entity.Vehicule;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VehiculeConverter {

    // Entity → DTO
    public VehiculeDTO toDto(Vehicule vehicule) {
        if (vehicule == null) return null;
        VehiculeDTO dto = new VehiculeDTO();
        dto.setId(vehicule.getId());
        dto.setImmatriculation(vehicule.getImmatriculation());
        dto.setModele(vehicule.getModele());
        dto.setType(vehicule.getType());
        dto.setKilometrage(vehicule.getKilometrage());
        dto.setStatut(vehicule.getStatut());
        dto.setImageData(vehicule.getImageData());
        return dto;
    }

    // DTO → Entity
    public Vehicule fromDto(VehiculeDTO dto) {
        if (dto == null) return null;
        Vehicule v = new Vehicule();
        v.setId(dto.getId());
        v.setImmatriculation(dto.getImmatriculation());
        v.setModele(dto.getModele());
        v.setType(dto.getType());
        v.setKilometrage(dto.getKilometrage());
        v.setStatut(dto.getStatut());
        v.setImageData(dto.getImageData());
        return v;
    }

    // List of entities → List of DTOs
    public List<VehiculeDTO> toDtoList(List<Vehicule> list) {
        return list.stream()
                   .map(this::toDto)
                   .collect(Collectors.toList());
    }
}

