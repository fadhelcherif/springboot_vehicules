package com.example.springboot_proj.controller;

import com.example.springboot_proj.dto.ChauffeurRequest;
import com.example.springboot_proj.dto.ChauffeurResponse;
import com.example.springboot_proj.service.ChauffeurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chauffeurs")
public class ChauffeurController {

    private final ChauffeurService chauffeurService;

    public ChauffeurController(ChauffeurService chauffeurService) {
        this.chauffeurService = chauffeurService;
    }

    @PostMapping
    public ResponseEntity<ChauffeurResponse> create(@RequestBody ChauffeurRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chauffeurService.create(request));
    }

    @GetMapping
    public List<ChauffeurResponse> getAll() {
        return chauffeurService.getAll();
    }

    @GetMapping("/{id}")
    public ChauffeurResponse getById(@PathVariable Long id) {
        return chauffeurService.getById(id);
    }

    @PutMapping("/{id}")
    public ChauffeurResponse update(@PathVariable Long id, @RequestBody ChauffeurRequest request) {
        return chauffeurService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        chauffeurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

