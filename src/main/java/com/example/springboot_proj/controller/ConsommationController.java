package com.example.springboot_proj.controller;

import com.example.springboot_proj.dto.ConsommationRequest;
import com.example.springboot_proj.dto.ConsommationResponse;
import com.example.springboot_proj.service.ConsommationService;
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
@RequestMapping("/api/consommations")
public class ConsommationController {

    private final ConsommationService consommationService;

    public ConsommationController(ConsommationService consommationService) {
        this.consommationService = consommationService;
    }

    @PostMapping
    public ResponseEntity<ConsommationResponse> create(@RequestBody ConsommationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consommationService.create(request));
    }

    @GetMapping
    public List<ConsommationResponse> getAll() {
        return consommationService.getAll();
    }

    @GetMapping("/{id}")
    public ConsommationResponse getById(@PathVariable Long id) {
        return consommationService.getById(id);
    }

    @PutMapping("/{id}")
    public ConsommationResponse update(@PathVariable Long id, @RequestBody ConsommationRequest request) {
        return consommationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consommationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

