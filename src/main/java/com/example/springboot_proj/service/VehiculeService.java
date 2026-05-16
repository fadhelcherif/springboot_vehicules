package com.example.springboot_proj.service;

import com.example.springboot_proj.converter.VehiculeConverter;
import com.example.springboot_proj.dto.MaintenanceAlertResponse;
import com.example.springboot_proj.dto.VehiculeDTO;
import com.example.springboot_proj.entity.Vehicule;
import com.example.springboot_proj.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private VehiculeConverter vehiculeConverter;

    public List<VehiculeDTO> getAll() {
        return vehiculeConverter.toDtoList(vehiculeRepository.findAll());
    }

    public VehiculeDTO getById(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id).get();
        return vehiculeConverter.toDto(vehicule);
    }

    public void save(VehiculeDTO dto) {
        Vehicule vehicule = vehiculeConverter.fromDto(dto);
        vehiculeRepository.save(vehicule);
    }

    public VehiculeDTO create(VehiculeDTO dto) {
        Vehicule vehicule = vehiculeConverter.fromDto(dto);
        return vehiculeConverter.toDto(vehiculeRepository.save(vehicule));
    }

    public VehiculeDTO update(Long id, VehiculeDTO dto) {
        Vehicule vehicule = vehiculeRepository.findById(id).get();
        vehicule.setImmatriculation(dto.getImmatriculation());
        vehicule.setModele(dto.getModele());
        vehicule.setType(dto.getType());
        vehicule.setKilometrage(dto.getKilometrage());
        vehicule.setStatut(dto.getStatut());
        vehicule.setImageData(dto.getImageData());
        return vehiculeConverter.toDto(vehiculeRepository.save(vehicule));
    }

    public void delete(Long id) {
        vehiculeRepository.deleteById(id);
    }

    public VehiculeDTO updateImage(Long id, String imageData) {
        Vehicule vehicule = vehiculeRepository.findById(id).get();
        vehicule.setImageData(imageData);
        return vehiculeConverter.toDto(vehiculeRepository.save(vehicule));
    }

    public List<MaintenanceAlertResponse> getMaintenanceAlerts(Long thresholdKm) {
        long seuil = thresholdKm == null ? 10000L : thresholdKm;
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
}

