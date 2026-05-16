package com.example.springboot_proj.converter;

import com.example.springboot_proj.dto.ChauffeurDTO;
import com.example.springboot_proj.entity.Chauffeur;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChauffeurConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ChauffeurDTO toDto(Chauffeur chauffeur) {
        ChauffeurDTO dto = modelMapper.map(chauffeur, ChauffeurDTO.class);
        return dto;
    }

    public Chauffeur fromDto(ChauffeurDTO dto) {
        return modelMapper.map(dto, Chauffeur.class);
    }

    public List<ChauffeurDTO> toDtoList(List<Chauffeur> list) {
        return list.stream()
                   .map(this::toDto)
                   .collect(Collectors.toList());
    }
}

