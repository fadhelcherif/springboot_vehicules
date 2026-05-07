package com.example.springboot_proj.service;

import com.example.springboot_proj.converter.ConsommationConverter;
import com.example.springboot_proj.dto.ConsommationDTO;
import com.example.springboot_proj.entity.Consommation;
import com.example.springboot_proj.entity.Vehicule;
import com.example.springboot_proj.repository.ConsommationRepository;
import com.example.springboot_proj.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ConsommationService {

    @Autowired
    private ConsommationRepository consommationRepository;

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private ConsommationConverter consommationConverter;

    public List<ConsommationDTO> getAll() {
        return consommationConverter.toDtoList(consommationRepository.findAll());
    }

    public ConsommationDTO getById(Long id) {
        Consommation consommation = consommationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Consommation introuvable avec id=" + id));
        return consommationConverter.toDto(consommation);
    }

    @Transactional
    public ConsommationDTO create(ConsommationDTO dto) {
        Vehicule vehicule = vehiculeRepository.findById(dto.getVehiculeId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vehicule introuvable avec id=" + dto.getVehiculeId()));

        Consommation consommation = new Consommation();
        consommation.setVehicule(vehicule);
        consommation.setDate(dto.getDate());
        consommation.setQuantiteCarburant(dto.getQuantiteCarburant());
        consommation.setCoutTotal(dto.getCoutTotal());

        return consommationConverter.toDto(consommationRepository.save(consommation));
    }

    @Transactional
    public ConsommationDTO update(Long id, ConsommationDTO dto) {
        Consommation consommation = consommationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Consommation introuvable avec id=" + id));

        if (dto.getVehiculeId() != null) {
            Vehicule vehicule = vehiculeRepository.findById(dto.getVehiculeId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Vehicule introuvable avec id=" + dto.getVehiculeId()));
            consommation.setVehicule(vehicule);
        }

        consommation.setDate(dto.getDate());
        consommation.setQuantiteCarburant(dto.getQuantiteCarburant());
        consommation.setCoutTotal(dto.getCoutTotal());

        return consommationConverter.toDto(consommationRepository.save(consommation));
    }

    @Transactional
    public void delete(Long id) {
        if (!consommationRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Consommation introuvable avec id=" + id);
        }
        consommationRepository.deleteById(id);
    }
}

