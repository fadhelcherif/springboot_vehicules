package com.example.springboot_proj.converter;

import com.example.springboot_proj.dto.MissionDTO;
import com.example.springboot_proj.entity.Mission;
import com.example.springboot_proj.entity.Vehicule;
import com.example.springboot_proj.entity.Chauffeur;
import com.example.springboot_proj.repository.VehiculeRepository;
import com.example.springboot_proj.repository.ChauffeurRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MissionConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private ChauffeurRepository chauffeurRepository;

    // Entity → DTO (maps only IDs, not full objects)
    public MissionDTO toDto(Mission mission) {
        MissionDTO dto = new MissionDTO();
        dto.setId(mission.getId());
        dto.setVehiculeId(mission.getVehicule() != null ? mission.getVehicule().getId() : null);
        dto.setChauffeurId(mission.getChauffeur() != null ? mission.getChauffeur().getId() : null);
        dto.setPointDepart(mission.getPointDepart());
        dto.setDestination(mission.getDestination());
        dto.setDistance(mission.getDistance());
        return dto;
    }

    // DTO → Entity (fetches entities by ID)
    public Mission fromDto(MissionDTO dto) {
        Mission mission = new Mission();
        mission.setId(dto.getId());
        if (dto.getVehiculeId() != null) {
            mission.setVehicule(vehiculeRepository.findById(dto.getVehiculeId()).orElse(null));
        }
        if (dto.getChauffeurId() != null) {
            mission.setChauffeur(chauffeurRepository.findById(dto.getChauffeurId()).orElse(null));
        }
        mission.setPointDepart(dto.getPointDepart());
        mission.setDestination(dto.getDestination());
        mission.setDistance(dto.getDistance());
        return mission;
    }

    // List of entities → List of DTOs
    public List<MissionDTO> toDtoList(List<Mission> list) {
        return list.stream()
                   .map(this::toDto)
                   .collect(Collectors.toList());
    }
}

