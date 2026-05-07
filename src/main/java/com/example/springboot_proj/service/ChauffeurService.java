package com.example.springboot_proj.service;

import com.example.springboot_proj.converter.ChauffeurConverter;
import com.example.springboot_proj.dto.ChauffeurDTO;
import com.example.springboot_proj.entity.Chauffeur;
import com.example.springboot_proj.repository.ChauffeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ChauffeurService {

    @Autowired
    private ChauffeurRepository chauffeurRepository;

    @Autowired
    private ChauffeurConverter chauffeurConverter;

    public List<ChauffeurDTO> getAll() {
        return chauffeurConverter.toDtoList(chauffeurRepository.findAll());
    }

    public ChauffeurDTO getById(Long id) {
        Chauffeur chauffeur = chauffeurRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Chauffeur introuvable avec id=" + id));
        return chauffeurConverter.toDto(chauffeur);
    }

    @Transactional
    public ChauffeurDTO create(ChauffeurDTO dto) {
        Chauffeur chauffeur = chauffeurConverter.fromDto(dto);
        return chauffeurConverter.toDto(chauffeurRepository.save(chauffeur));
    }

    @Transactional
    public ChauffeurDTO update(Long id, ChauffeurDTO dto) {
        Chauffeur chauffeur = chauffeurRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Chauffeur introuvable avec id=" + id));
        chauffeur.setNom(dto.getNom());
        chauffeur.setPermis(dto.getPermis());
        chauffeur.setExperience(dto.getExperience());
        chauffeur.setImageData(dto.getImageData());
        return chauffeurConverter.toDto(chauffeurRepository.save(chauffeur));
    }

    @Transactional
    public void delete(Long id) {
        if (!chauffeurRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Chauffeur introuvable avec id=" + id);
        }
        chauffeurRepository.deleteById(id);
    }

    @Transactional
    public ChauffeurDTO updateImage(Long id, String imageData) {
        Chauffeur chauffeur = chauffeurRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Chauffeur introuvable avec id=" + id));
        chauffeur.setImageData(imageData);
        return chauffeurConverter.toDto(chauffeurRepository.save(chauffeur));
    }
}

