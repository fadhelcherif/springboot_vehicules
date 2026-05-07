package com.example.springboot_proj.converter;

import com.example.springboot_proj.dto.ConsommationDTO;
import com.example.springboot_proj.entity.Consommation;
import com.example.springboot_proj.repository.VehiculeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConsommationConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VehiculeRepository vehiculeRepository;

    // Entity → DTO (maps only ID, not full object)
    public ConsommationDTO toDto(Consommation consommation) {
        ConsommationDTO dto = new ConsommationDTO();
        dto.setId(consommation.getId());
        dto.setVehiculeId(consommation.getVehicule() != null ? consommation.getVehicule().getId() : null);
        dto.setDate(consommation.getDate());
        dto.setQuantiteCarburant(consommation.getQuantiteCarburant());
        dto.setCoutTotal(consommation.getCoutTotal());
        return dto;
    }

    // DTO → Entity (fetches entity by ID)
    public Consommation fromDto(ConsommationDTO dto) {
        Consommation consommation = new Consommation();
        consommation.setId(dto.getId());
        if (dto.getVehiculeId() != null) {
            consommation.setVehicule(vehiculeRepository.findById(dto.getVehiculeId()).orElse(null));
        }
        consommation.setDate(dto.getDate());
        consommation.setQuantiteCarburant(dto.getQuantiteCarburant());
        consommation.setCoutTotal(dto.getCoutTotal());
        return consommation;
    }

    // List of entities → List of DTOs
    public List<ConsommationDTO> toDtoList(List<Consommation> list) {
        return list.stream()
                   .map(this::toDto)
                   .collect(Collectors.toList());
    }
}

