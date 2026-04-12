package com.example.springboot_proj.controller;

import com.example.springboot_proj.dto.MaintenanceAlertResponse;
import com.example.springboot_proj.dto.VehiculeRequest;
import com.example.springboot_proj.dto.VehiculeResponse;
import com.example.springboot_proj.service.VehiculeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @PostMapping
    public ResponseEntity<VehiculeResponse> create(@RequestBody VehiculeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculeService.create(request));
    }

    @GetMapping
    public List<VehiculeResponse> getAll() {
        return vehiculeService.getAll();
    }

    @GetMapping("/{id}")
    public VehiculeResponse getById(@PathVariable Long id) {
        return vehiculeService.getById(id);
    }

    @PutMapping("/{id}")
    public VehiculeResponse update(@PathVariable Long id, @RequestBody VehiculeRequest request) {
        return vehiculeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehiculeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/maintenance/alerts")
    public List<MaintenanceAlertResponse> getMaintenanceAlerts(@RequestParam(required = false) Long thresholdKm) {
        return vehiculeService.getMaintenanceAlerts(thresholdKm);
    }
}

