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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
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
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Mission introuvable avec id=" + id));
        return missionConverter.toDto(mission);
    }

    @Transactional
    public MissionDTO create(MissionDTO dto) {
        Vehicule vehicule = vehiculeRepository.findById(dto.getVehiculeId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vehicule introuvable avec id=" + dto.getVehiculeId()));
        Chauffeur chauffeur = chauffeurRepository.findById(dto.getChauffeurId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Chauffeur introuvable avec id=" + dto.getChauffeurId()));

        Mission mission = new Mission();
        mission.setVehicule(vehicule);
        mission.setChauffeur(chauffeur);
        mission.setPointDepart(dto.getPointDepart());
        mission.setDestination(dto.getDestination());
        mission.setDistance(dto.getDistance());

        return missionConverter.toDto(missionRepository.save(mission));
    }

    @Transactional
    public MissionDTO update(Long id, MissionDTO dto) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Mission introuvable avec id=" + id));

        if (dto.getVehiculeId() != null) {
            Vehicule vehicule = vehiculeRepository.findById(dto.getVehiculeId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Vehicule introuvable avec id=" + dto.getVehiculeId()));
            mission.setVehicule(vehicule);
        }

        if (dto.getChauffeurId() != null) {
            Chauffeur chauffeur = chauffeurRepository.findById(dto.getChauffeurId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Chauffeur introuvable avec id=" + dto.getChauffeurId()));
            mission.setChauffeur(chauffeur);
        }

        mission.setPointDepart(dto.getPointDepart());
        mission.setDestination(dto.getDestination());
        mission.setDistance(dto.getDistance());

        return missionConverter.toDto(missionRepository.save(mission));
    }

    @Transactional
    public void delete(Long id) {
        if (!missionRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Mission introuvable avec id=" + id);
        }
        missionRepository.deleteById(id);
    }
}

