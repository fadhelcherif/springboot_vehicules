package com.example.springboot_proj.controller;

import com.example.springboot_proj.dto.ConsommationRequest;
import com.example.springboot_proj.dto.ConsommationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Consommations", description = "CRUD des consommations de carburant")
public class ConsommationController {

    private final ConsommationService consommationService;

    public ConsommationController(ConsommationService consommationService) {
        this.consommationService = consommationService;
    }

    @PostMapping
    @Operation(summary = "Creer une consommation", description = "Enregistre une consommation de carburant pour un vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Consommation creee", content = @Content(schema = @Schema(implementation = ConsommationResponse.class))),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    public ResponseEntity<ConsommationResponse> create(@RequestBody ConsommationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consommationService.create(request));
    }

    @GetMapping
    @Operation(summary = "Lister les consommations", description = "Retourne toutes les consommations")
    @ApiResponse(responseCode = "200", description = "Liste des consommations", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ConsommationResponse.class))))
    public List<ConsommationResponse> getAll() {
        return consommationService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperer une consommation", description = "Retourne une consommation par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consommation trouvee", content = @Content(schema = @Schema(implementation = ConsommationResponse.class))),
            @ApiResponse(responseCode = "404", description = "Consommation introuvable")
    })
    public ConsommationResponse getById(@Parameter(description = "Identifiant de la consommation", example = "1") @PathVariable Long id) {
        return consommationService.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre a jour une consommation", description = "Modifie une consommation existante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consommation mise a jour", content = @Content(schema = @Schema(implementation = ConsommationResponse.class))),
            @ApiResponse(responseCode = "404", description = "Consommation ou vehicule introuvable")
    })
    public ConsommationResponse update(@Parameter(description = "Identifiant de la consommation", example = "1") @PathVariable Long id, @RequestBody ConsommationRequest request) {
        return consommationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une consommation", description = "Supprime une consommation par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Consommation supprimee"),
            @ApiResponse(responseCode = "404", description = "Consommation introuvable")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Identifiant de la consommation", example = "1") @PathVariable Long id) {
        consommationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

