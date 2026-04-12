package com.example.springboot_proj.service;

import com.example.springboot_proj.dto.AffectationRequest;
import com.example.springboot_proj.dto.MissionRequest;
import com.example.springboot_proj.dto.MissionResponse;
import com.example.springboot_proj.model.Chauffeur;
import com.example.springboot_proj.model.Mission;
import com.example.springboot_proj.model.Vehicule;
import com.example.springboot_proj.repository.MissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final VehiculeService vehiculeService;
    private final ChauffeurService chauffeurService;

    public MissionService(MissionRepository missionRepository, VehiculeService vehiculeService, ChauffeurService chauffeurService) {
        this.missionRepository = missionRepository;
        this.vehiculeService = vehiculeService;
        this.chauffeurService = chauffeurService;
    }

    @Transactional
    public MissionResponse create(MissionRequest request) {
        Mission mission = new Mission();
        applyRequest(mission, request);
        return toResponse(missionRepository.save(mission));
    }

    public List<MissionResponse> getAll() {
        return missionRepository.findAll().stream().map(this::toResponse).toList();
    }

    public MissionResponse getById(Long id) {
        return toResponse(findEntity(id));
    }

    @Transactional
    public MissionResponse update(Long id, MissionRequest request) {
        Mission mission = findEntity(id);
        applyRequest(mission, request);
        return toResponse(missionRepository.save(mission));
    }

    @Transactional
    public MissionResponse updateAffectation(Long missionId, AffectationRequest request) {
        Mission mission = findEntity(missionId);
        Vehicule vehicule = vehiculeService.findEntity(request.vehiculeId());
        Chauffeur chauffeur = chauffeurService.findEntity(request.chauffeurId());
        mission.setVehicule(vehicule);
        mission.setChauffeur(chauffeur);
        return toResponse(missionRepository.save(mission));
    }

    @Transactional
    public void delete(Long id) {
        if (!missionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Mission introuvable avec id=" + id);
        }
        missionRepository.deleteById(id);
    }

    public Mission findEntity(Long id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mission introuvable avec id=" + id));
    }

    private void applyRequest(Mission mission, MissionRequest request) {
        Vehicule vehicule = vehiculeService.findEntity(request.vehiculeId());
        Chauffeur chauffeur = chauffeurService.findEntity(request.chauffeurId());
        mission.setVehicule(vehicule);
        mission.setChauffeur(chauffeur);
        mission.setPointDepart(request.pointDepart());
        mission.setDestination(request.destination());
        mission.setDistance(request.distance());
    }

    private MissionResponse toResponse(Mission mission) {
        return new MissionResponse(
                mission.getId(),
                mission.getVehicule().getId(),
                mission.getVehicule().getImmatriculation(),
                mission.getChauffeur().getId(),
                mission.getChauffeur().getNom(),
                mission.getPointDepart(),
                mission.getDestination(),
                mission.getDistance()
        );
    }
}

