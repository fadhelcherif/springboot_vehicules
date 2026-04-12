package com.example.springboot_proj.service;

import com.example.springboot_proj.dto.ConsommationRequest;
import com.example.springboot_proj.dto.ConsommationResponse;
import com.example.springboot_proj.model.Consommation;
import com.example.springboot_proj.model.Vehicule;
import com.example.springboot_proj.repository.ConsommationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ConsommationService {

    private final ConsommationRepository consommationRepository;
    private final VehiculeService vehiculeService;

    public ConsommationService(ConsommationRepository consommationRepository, VehiculeService vehiculeService) {
        this.consommationRepository = consommationRepository;
        this.vehiculeService = vehiculeService;
    }

    @Transactional
    public ConsommationResponse create(ConsommationRequest request) {
        Consommation consommation = new Consommation();
        applyRequest(consommation, request);
        return toResponse(consommationRepository.save(consommation));
    }

    public List<ConsommationResponse> getAll() {
        return consommationRepository.findAll().stream().map(this::toResponse).toList();
    }

    public ConsommationResponse getById(Long id) {
        return toResponse(findEntity(id));
    }

    @Transactional
    public ConsommationResponse update(Long id, ConsommationRequest request) {
        Consommation consommation = findEntity(id);
        applyRequest(consommation, request);
        return toResponse(consommationRepository.save(consommation));
    }

    @Transactional
    public void delete(Long id) {
        if (!consommationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consommation introuvable avec id=" + id);
        }
        consommationRepository.deleteById(id);
    }

    public Consommation findEntity(Long id) {
        return consommationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consommation introuvable avec id=" + id));
    }

    private void applyRequest(Consommation consommation, ConsommationRequest request) {
        Vehicule vehicule = vehiculeService.findEntity(request.vehiculeId());
        consommation.setVehicule(vehicule);
        consommation.setDate(request.date());
        consommation.setQuantiteCarburant(request.quantiteCarburant());
        consommation.setCoutTotal(request.coutTotal());
    }

    private ConsommationResponse toResponse(Consommation consommation) {
        return new ConsommationResponse(
                consommation.getId(),
                consommation.getVehicule().getId(),
                consommation.getVehicule().getImmatriculation(),
                consommation.getDate(),
                consommation.getQuantiteCarburant(),
                consommation.getCoutTotal()
        );
    }
}

