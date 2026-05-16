package com.example.springboot_proj.service;

import com.example.springboot_proj.converter.MissionConverter;
import com.example.springboot_proj.dto.MissionDTO;
import com.example.springboot_proj.entity.Mission;
import com.example.springboot_proj.entity.Vehicule;
import com.example.springboot_proj.entity.Chauffeur;
import com.example.springboot_proj.repository.MissionRepository;
import com.example.springboot_proj.repository.VehiculeRepository;
import com.example.springboot_proj.repository.ChauffeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private ChauffeurRepository chauffeurRepository;

    @Autowired
    private MissionConverter missionConverter;

    public List<MissionDTO> getAll() {
        return missionConverter.toDtoList(missionRepository.findAll());
    }

    public MissionDTO getById(Long id) {
        Mission mission = missionRepository.findById(id).get();
        return missionConverter.toDto(mission);
    }

    public void save(MissionDTO dto) {
        Mission mission = new Mission();
        mission.setVehicule(vehiculeRepository.findById(dto.getVehiculeId()).get());
        mission.setChauffeur(chauffeurRepository.findById(dto.getChauffeurId()).get());
        mission.setPointDepart(dto.getPointDepart());
        mission.setDestination(dto.getDestination());
        mission.setDistance(dto.getDistance());
        missionRepository.save(mission);
    }

    public MissionDTO create(MissionDTO dto) {
        Mission mission = new Mission();
        mission.setVehicule(vehiculeRepository.findById(dto.getVehiculeId()).get());
        mission.setChauffeur(chauffeurRepository.findById(dto.getChauffeurId()).get());
        mission.setPointDepart(dto.getPointDepart());
        mission.setDestination(dto.getDestination());
        mission.setDistance(dto.getDistance());

        return missionConverter.toDto(missionRepository.save(mission));
    }

    public MissionDTO update(Long id, MissionDTO dto) {
        Mission mission = missionRepository.findById(id).get();

        Vehicule vehicule = vehiculeRepository.findById(dto.getVehiculeId()).get();
        Chauffeur chauffeur = chauffeurRepository.findById(dto.getChauffeurId()).get();
        mission.setVehicule(vehicule);
        mission.setChauffeur(chauffeur);

        mission.setPointDepart(dto.getPointDepart());
        mission.setDestination(dto.getDestination());
        mission.setDistance(dto.getDistance());

        return missionConverter.toDto(missionRepository.save(mission));
    }

    public void delete(Long id) {
        missionRepository.deleteById(id);
    }
}

