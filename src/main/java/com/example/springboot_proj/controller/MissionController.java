package com.example.springboot_proj.controller;

import com.example.springboot_proj.dto.AffectationRequest;
import com.example.springboot_proj.dto.MissionRequest;
import com.example.springboot_proj.dto.MissionResponse;
import com.example.springboot_proj.service.MissionService;
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
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping
    public ResponseEntity<MissionResponse> create(@RequestBody MissionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(missionService.create(request));
    }

    @GetMapping
    public List<MissionResponse> getAll() {
        return missionService.getAll();
    }

    @GetMapping("/{id}")
    public MissionResponse getById(@PathVariable Long id) {
        return missionService.getById(id);
    }

    @PutMapping("/{id}")
    public MissionResponse update(@PathVariable Long id, @RequestBody MissionRequest request) {
        return missionService.update(id, request);
    }

    @PutMapping("/{id}/affectation")
    public MissionResponse updateAffectation(@PathVariable Long id, @RequestBody AffectationRequest request) {
        return missionService.updateAffectation(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        missionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

