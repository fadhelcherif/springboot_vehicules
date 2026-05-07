package com.example.springboot_proj.service;

import com.example.springboot_proj.converter.VehiculeConverter;
import com.example.springboot_proj.dto.MaintenanceAlertResponse;
import com.example.springboot_proj.dto.VehiculeDTO;
import com.example.springboot_proj.entity.Vehicule;
import com.example.springboot_proj.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private VehiculeConverter vehiculeConverter;

    public List<VehiculeDTO> getAll() {
        return vehiculeConverter.toDtoList(vehiculeRepository.findAll());
    }

    public VehiculeDTO getById(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vehicule introuvable avec id=" + id));
        return vehiculeConverter.toDto(vehicule);
    }

    @Transactional
    public VehiculeDTO create(VehiculeDTO dto) {
        if (vehiculeRepository.existsByImmatriculation(dto.getImmatriculation())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Un vehicule avec l'immatriculation " + dto.getImmatriculation() + " existe deja");
        }
        Vehicule vehicule = vehiculeConverter.fromDto(dto);
        if (vehicule.getStatut() == null) {
            vehicule.setStatut("DISPONIBLE");
        }
        return vehiculeConverter.toDto(vehiculeRepository.save(vehicule));
    }

    @Transactional
    public VehiculeDTO update(Long id, VehiculeDTO dto) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vehicule introuvable avec id=" + id));
        vehicule.setImmatriculation(dto.getImmatriculation());
        vehicule.setModele(dto.getModele());
        vehicule.setType(dto.getType());
        vehicule.setKilometrage(dto.getKilometrage());
        vehicule.setStatut(dto.getStatut());
        vehicule.setImageData(dto.getImageData());
        return vehiculeConverter.toDto(vehiculeRepository.save(vehicule));
    }

    @Transactional
    public void delete(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Vehicule introuvable avec id=" + id);
        }
        vehiculeRepository.deleteById(id);
    }

    @Transactional
    public VehiculeDTO updateImage(Long id, String imageData) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vehicule introuvable avec id=" + id));
        vehicule.setImageData(imageData);
        return vehiculeConverter.toDto(vehiculeRepository.save(vehicule));
    }

    public List<MaintenanceAlertResponse> getMaintenanceAlerts(Long thresholdKm) {
        long seuil = thresholdKm == null ? 10000L : thresholdKm;
        if (seuil < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Le seuil de kilometrage doit etre positif");
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
}

