package com.example.springboot_proj.controller;

import com.example.springboot_proj.dto.MaintenanceAlertResponse;
import com.example.springboot_proj.dto.VehiculeRequest;
import com.example.springboot_proj.dto.VehiculeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Vehicules", description = "CRUD des vehicules et suivi de maintenance preventive")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @PostMapping
    @Operation(summary = "Creer un vehicule", description = "Ajoute un nouveau vehicule dans la flotte")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vehicule cree", content = @Content(schema = @Schema(implementation = VehiculeResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requete invalide")
    })
    public ResponseEntity<VehiculeResponse> create(@RequestBody VehiculeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculeService.create(request));
    }

    @GetMapping
    @Operation(summary = "Lister les vehicules", description = "Retourne tous les vehicules de la flotte")
    @ApiResponse(responseCode = "200", description = "Liste des vehicules", content = @Content(array = @ArraySchema(schema = @Schema(implementation = VehiculeResponse.class))))
    public List<VehiculeResponse> getAll() {
        return vehiculeService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperer un vehicule", description = "Retourne un vehicule par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule trouve", content = @Content(schema = @Schema(implementation = VehiculeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    public VehiculeResponse getById(@Parameter(description = "Identifiant du vehicule", example = "1") @PathVariable Long id) {
        return vehiculeService.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre a jour un vehicule", description = "Modifie les informations d'un vehicule existant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule mis a jour", content = @Content(schema = @Schema(implementation = VehiculeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    public VehiculeResponse update(@Parameter(description = "Identifiant du vehicule", example = "1") @PathVariable Long id, @RequestBody VehiculeRequest request) {
        return vehiculeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un vehicule", description = "Supprime un vehicule par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Vehicule supprime"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Identifiant du vehicule", example = "1") @PathVariable Long id) {
        vehiculeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/maintenance/alerts")
    @Operation(summary = "Alertes maintenance preventive", description = "Retourne les vehicules dont le kilometrage depasse un seuil")
    @ApiResponse(responseCode = "200", description = "Liste des alertes", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MaintenanceAlertResponse.class))))
    public List<MaintenanceAlertResponse> getMaintenanceAlerts(
            @Parameter(description = "Seuil de kilometrage (par defaut 10000)", example = "10000")
            @RequestParam(required = false) Long thresholdKm) {
        return vehiculeService.getMaintenanceAlerts(thresholdKm);
    }
}

