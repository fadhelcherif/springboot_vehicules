package com.example.springboot_proj.service;

import com.example.springboot_proj.converter.ChauffeurConverter;
import com.example.springboot_proj.dto.ChauffeurDTO;
import com.example.springboot_proj.entity.Chauffeur;
import com.example.springboot_proj.repository.ChauffeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChauffeurService {

    @Autowired
    private ChauffeurRepository chauffeurRepository;

    @Autowired
    private ChauffeurConverter chauffeurConverter;

    public List<ChauffeurDTO> getAll() {
        return chauffeurConverter.toDtoList(chauffeurRepository.findAll());
    }

    public ChauffeurDTO getById(Long id) {
        Chauffeur chauffeur = chauffeurRepository.findById(id).get();
        return chauffeurConverter.toDto(chauffeur);
    }

    public void save(ChauffeurDTO dto) {
        Chauffeur chauffeur = chauffeurConverter.fromDto(dto);
        chauffeurRepository.save(chauffeur);
    }

    public ChauffeurDTO create(ChauffeurDTO dto) {
        Chauffeur chauffeur = chauffeurConverter.fromDto(dto);
        return chauffeurConverter.toDto(chauffeurRepository.save(chauffeur));
    }

    public ChauffeurDTO update(Long id, ChauffeurDTO dto) {
        Chauffeur chauffeur = chauffeurRepository.findById(id).get();
        chauffeur.setNom(dto.getNom());
        chauffeur.setPermis(dto.getPermis());
        chauffeur.setExperience(dto.getExperience());
        chauffeur.setImageData(dto.getImageData());
        return chauffeurConverter.toDto(chauffeurRepository.save(chauffeur));
    }

    public void delete(Long id) {
        chauffeurRepository.deleteById(id);
    }

    public ChauffeurDTO updateImage(Long id, String imageData) {
        Chauffeur chauffeur = chauffeurRepository.findById(id).get();
        chauffeur.setImageData(imageData);
        return chauffeurConverter.toDto(chauffeurRepository.save(chauffeur));
    }
}

