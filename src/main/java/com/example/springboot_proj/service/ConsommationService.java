package com.example.springboot_proj.service;

import com.example.springboot_proj.converter.ConsommationConverter;
import com.example.springboot_proj.dto.ConsommationDTO;
import com.example.springboot_proj.entity.Consommation;
import com.example.springboot_proj.entity.Vehicule;
import com.example.springboot_proj.repository.ConsommationRepository;
import com.example.springboot_proj.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        Consommation consommation = consommationRepository.findById(id).get();
        return consommationConverter.toDto(consommation);
    }

    public void save(ConsommationDTO dto) {
        Consommation consommation = new Consommation();
        consommation.setVehicule(vehiculeRepository.findById(dto.getVehiculeId()).get());
        consommation.setDate(dto.getDate());
        consommation.setQuantiteCarburant(dto.getQuantiteCarburant());
        consommation.setCoutTotal(dto.getCoutTotal());
        consommationRepository.save(consommation);
    }

    public ConsommationDTO create(ConsommationDTO dto) {
        Consommation consommation = new Consommation();
        consommation.setVehicule(vehiculeRepository.findById(dto.getVehiculeId()).get());
        consommation.setDate(dto.getDate());
        consommation.setQuantiteCarburant(dto.getQuantiteCarburant());
        consommation.setCoutTotal(dto.getCoutTotal());

        return consommationConverter.toDto(consommationRepository.save(consommation));
    }

    public ConsommationDTO update(Long id, ConsommationDTO dto) {
        Consommation consommation = consommationRepository.findById(id).get();

        Vehicule vehicule = vehiculeRepository.findById(dto.getVehiculeId()).get();
        consommation.setVehicule(vehicule);

        consommation.setDate(dto.getDate());
        consommation.setQuantiteCarburant(dto.getQuantiteCarburant());
        consommation.setCoutTotal(dto.getCoutTotal());

        return consommationConverter.toDto(consommationRepository.save(consommation));
    }

    public void delete(Long id) {
        consommationRepository.deleteById(id);
    }
}

