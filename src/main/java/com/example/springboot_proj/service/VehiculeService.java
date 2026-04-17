package com.example.springboot_proj.service;

import com.example.springboot_proj.dto.MaintenanceAlertResponse;
import com.example.springboot_proj.dto.VehiculeRequest;
import com.example.springboot_proj.dto.VehiculeResponse;
import com.example.springboot_proj.model.Vehicule;
import com.example.springboot_proj.repository.VehiculeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    public VehiculeService(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    @Transactional
    public VehiculeResponse create(VehiculeRequest request) {
        Vehicule vehicule = new Vehicule();
        applyRequest(vehicule, request);
        return toResponse(vehiculeRepository.save(vehicule));
    }

    public List<VehiculeResponse> getAll() {
        return vehiculeRepository.findAll().stream().map(this::toResponse).toList();
    }

    public VehiculeResponse getById(Long id) {
        return toResponse(findEntity(id));
    }

    @Transactional
    public VehiculeResponse update(Long id, VehiculeRequest request) {
        Vehicule vehicule = findEntity(id);
        applyRequest(vehicule, request);
        return toResponse(vehiculeRepository.save(vehicule));
    }

    @Transactional
    public void delete(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule introuvable avec id=" + id);
        }
        vehiculeRepository.deleteById(id);
    }

    @Transactional
    public VehiculeResponse updateImage(Long id, String imageData) {
        Vehicule vehicule = findEntity(id);
        vehicule.setImageData(imageData);
        return toResponse(vehiculeRepository.save(vehicule));
    }

    public List<MaintenanceAlertResponse> getMaintenanceAlerts(Long thresholdKm) {
        long seuil = thresholdKm == null ? 10000L : thresholdKm;
        if (seuil < 0) {
            throw new IllegalArgumentException("Le seuil de kilometrage doit etre positif");
        }
        return vehiculeRepository.findByKilometrageGreaterThanEqualOrderByKilometrageDesc(seuil)
                .stream()
                .map(v -> new MaintenanceAlertResponse(
                        v.getId(),
                        v.getImmatriculation(),
                        v.getModele(),
                        v.getKilometrage(),
                        seuil,
                        Math.max(0L, v.getKilometrage() - seuil),
                        v.getStatut()
                ))
                .toList();
    }

    public Vehicule findEntity(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule introuvable avec id=" + id));
    }

    private void applyRequest(Vehicule vehicule, VehiculeRequest request) {
        vehicule.setImmatriculation(request.immatriculation());
        vehicule.setModele(request.modele());
        vehicule.setType(request.type());
        vehicule.setKilometrage(request.kilometrage());
        vehicule.setStatut(request.statut());
        vehicule.setImageData(request.imageData());
    }

    private VehiculeResponse toResponse(Vehicule vehicule) {
        return new VehiculeResponse(
                vehicule.getId(),
                vehicule.getImmatriculation(),
                vehicule.getModele(),
                vehicule.getType(),
                vehicule.getKilometrage(),
                vehicule.getStatut(),
                vehicule.getImageData()
        );
    }
}

