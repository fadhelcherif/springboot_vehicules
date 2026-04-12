package com.example.springboot_proj.service;

import com.example.springboot_proj.dto.ChauffeurRequest;
import com.example.springboot_proj.dto.ChauffeurResponse;
import com.example.springboot_proj.model.Chauffeur;
import com.example.springboot_proj.repository.ChauffeurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ChauffeurService {

    private final ChauffeurRepository chauffeurRepository;

    public ChauffeurService(ChauffeurRepository chauffeurRepository) {
        this.chauffeurRepository = chauffeurRepository;
    }

    @Transactional
    public ChauffeurResponse create(ChauffeurRequest request) {
        Chauffeur chauffeur = new Chauffeur();
        applyRequest(chauffeur, request);
        return toResponse(chauffeurRepository.save(chauffeur));
    }

    public List<ChauffeurResponse> getAll() {
        return chauffeurRepository.findAll().stream().map(this::toResponse).toList();
    }

    public ChauffeurResponse getById(Long id) {
        return toResponse(findEntity(id));
    }

    @Transactional
    public ChauffeurResponse update(Long id, ChauffeurRequest request) {
        Chauffeur chauffeur = findEntity(id);
        applyRequest(chauffeur, request);
        return toResponse(chauffeurRepository.save(chauffeur));
    }

    @Transactional
    public void delete(Long id) {
        if (!chauffeurRepository.existsById(id)) {
            throw new ResourceNotFoundException("Chauffeur introuvable avec id=" + id);
        }
        chauffeurRepository.deleteById(id);
    }

    public Chauffeur findEntity(Long id) {
        return chauffeurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chauffeur introuvable avec id=" + id));
    }

    private void applyRequest(Chauffeur chauffeur, ChauffeurRequest request) {
        chauffeur.setNom(request.nom());
        chauffeur.setPermis(request.permis());
        chauffeur.setExperience(request.experience());
    }

    private ChauffeurResponse toResponse(Chauffeur chauffeur) {
        return new ChauffeurResponse(
                chauffeur.getId(),
                chauffeur.getNom(),
                chauffeur.getPermis(),
                chauffeur.getExperience()
        );
    }
}

