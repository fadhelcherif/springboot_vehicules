package com.example.springboot_proj.controller;

import com.example.springboot_proj.dto.ApiResponseDTO;
import com.example.springboot_proj.dto.MaintenanceAlertResponse;
import com.example.springboot_proj.dto.VehiculeDTO;
import com.example.springboot_proj.service.VehiculeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/vehicules")
@Tag(name = "Vehicules", description = "CRUD des vehicules et suivi de maintenance preventive")
public class VehiculeController {

    @Autowired
    private VehiculeService vehiculeService;

    @PostMapping
    @Operation(summary = "Creer un vehicule", description = "Ajoute un nouveau vehicule dans la flotte")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vehicule cree", content = @Content(schema = @Schema(implementation = VehiculeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requete invalide")
    })
    public ResponseEntity<ApiResponseDTO<VehiculeDTO>> create(@Valid @RequestBody VehiculeDTO dto) {
        VehiculeDTO created = vehiculeService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDTO.ok("Vehicule cree avec succes", created));
    }

    @GetMapping
    @Operation(summary = "Lister les vehicules", description = "Retourne tous les vehicules de la flotte")
    @ApiResponse(responseCode = "200", description = "Liste des vehicules", content = @Content(array = @ArraySchema(schema = @Schema(implementation = VehiculeDTO.class))))
    public ResponseEntity<ApiResponseDTO<List<VehiculeDTO>>> getAll() {
        return ResponseEntity.ok(ApiResponseDTO.ok(vehiculeService.getAll()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperer un vehicule", description = "Retourne un vehicule par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule trouve", content = @Content(schema = @Schema(implementation = VehiculeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    public ResponseEntity<ApiResponseDTO<VehiculeDTO>> getById(@Parameter(description = "Identifiant du vehicule", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponseDTO.ok(vehiculeService.getById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre a jour un vehicule", description = "Modifie les informations d'un vehicule existant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule mis a jour", content = @Content(schema = @Schema(implementation = VehiculeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    public ResponseEntity<ApiResponseDTO<VehiculeDTO>> update(@Parameter(description = "Identifiant du vehicule", example = "1") @PathVariable Long id, @Valid @RequestBody VehiculeDTO dto) {
        return ResponseEntity.ok(ApiResponseDTO.ok("Mis a jour", vehiculeService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un vehicule", description = "Supprime un vehicule par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule supprime"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    public ResponseEntity<ApiResponseDTO<Void>> delete(@Parameter(description = "Identifiant du vehicule", example = "1") @PathVariable Long id) {
        vehiculeService.delete(id);
        return ResponseEntity.ok(ApiResponseDTO.ok("Supprime avec succes", null));
    }

    @GetMapping("/maintenance/alerts")
    @Operation(summary = "Alertes maintenance preventive", description = "Retourne les vehicules dont le kilometrage depasse un seuil")
    @ApiResponse(responseCode = "200", description = "Liste des alertes", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MaintenanceAlertResponse.class))))
    public ResponseEntity<ApiResponseDTO<List<MaintenanceAlertResponse>>> getMaintenanceAlerts(
            @Parameter(description = "Seuil de kilometrage (par defaut 10000)", example = "10000")
            @RequestParam(required = false) Long thresholdKm) {
        return ResponseEntity.ok(ApiResponseDTO.ok(vehiculeService.getMaintenanceAlerts(thresholdKm)));
    }

    @PostMapping(value = "/{id}/image", consumes = "multipart/form-data")
    @Operation(
        summary = "Uploader une image pour le vehicule",
        description = "Permet de charger une image (photo du vehicule) pour un vehicule existant."
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Image uploadee avec succes",
                content = @Content(schema = @Schema(implementation = VehiculeDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable"),
            @ApiResponse(responseCode = "400", description = "Fichier invalide")
    })
    public ResponseEntity<ApiResponseDTO<VehiculeDTO>> uploadImage(
            @Parameter(description = "ID du vehicule", example = "1")
            @PathVariable Long id,
            @Parameter(
                description = "Fichier image",
                content = @Content(mediaType = "application/octet-stream", schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart("file") MultipartFile file) throws IOException {
        String imageData = Base64.getEncoder().encodeToString(file.getBytes());
        return ResponseEntity.ok(ApiResponseDTO.ok("Image uploadee", vehiculeService.updateImage(id, imageData)));
    }
}

