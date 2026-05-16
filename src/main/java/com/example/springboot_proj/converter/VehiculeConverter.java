package com.example.springboot_proj.converter;

import com.example.springboot_proj.dto.VehiculeDTO;
import com.example.springboot_proj.entity.Vehicule;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VehiculeConverter {

    @Autowired
    private ModelMapper modelMapper;

    public VehiculeDTO toDto(Vehicule vehicule) {
        VehiculeDTO dto = modelMapper.map(vehicule, VehiculeDTO.class);
        return dto;
    }

    public Vehicule fromDto(VehiculeDTO dto) {
        return modelMapper.map(dto, Vehicule.class);
    }

    public List<VehiculeDTO> toDtoList(List<Vehicule> list) {
        return list.stream()
                   .map(this::toDto)
                   .collect(Collectors.toList());
    }
}

