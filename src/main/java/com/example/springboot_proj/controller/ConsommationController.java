package com.example.springboot_proj.controller;

import com.example.springboot_proj.dto.ApiResponseDTO;
import com.example.springboot_proj.dto.ConsommationDTO;
import com.example.springboot_proj.service.ConsommationService;
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

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/consommations")
@Tag(name = "Consommations", description = "CRUD des consommations de carburant")
public class ConsommationController {

    @Autowired
    private ConsommationService consommationService;

    @PostMapping
    @Operation(summary = "Creer une consommation", description = "Enregistre une consommation de carburant pour un vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Consommation creee", content = @Content(schema = @Schema(implementation = ConsommationDTO.class))),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    public ResponseEntity<ApiResponseDTO<ConsommationDTO>> create(@Valid @RequestBody ConsommationDTO dto) {
        ConsommationDTO created = consommationService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDTO.ok("Consommation creee avec succes", created));
    }

    @GetMapping
    @Operation(summary = "Lister les consommations", description = "Retourne toutes les consommations")
    @ApiResponse(responseCode = "200", description = "Liste des consommations", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ConsommationDTO.class))))
    public ResponseEntity<ApiResponseDTO<List<ConsommationDTO>>> getAll() {
        return ResponseEntity.ok(ApiResponseDTO.ok(consommationService.getAll()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperer une consommation", description = "Retourne une consommation par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consommation trouvee", content = @Content(schema = @Schema(implementation = ConsommationDTO.class))),
            @ApiResponse(responseCode = "404", description = "Consommation introuvable")
    })
    public ResponseEntity<ApiResponseDTO<ConsommationDTO>> getById(@Parameter(description = "Identifiant de la consommation", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponseDTO.ok(consommationService.getById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre a jour une consommation", description = "Modifie une consommation existante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consommation mise a jour", content = @Content(schema = @Schema(implementation = ConsommationDTO.class))),
            @ApiResponse(responseCode = "404", description = "Consommation ou vehicule introuvable")
    })
    public ResponseEntity<ApiResponseDTO<ConsommationDTO>> update(@Parameter(description = "Identifiant de la consommation", example = "1") @PathVariable Long id, @Valid @RequestBody ConsommationDTO dto) {
        return ResponseEntity.ok(ApiResponseDTO.ok("Mis a jour", consommationService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une consommation", description = "Supprime une consommation par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consommation supprimee"),
            @ApiResponse(responseCode = "404", description = "Consommation introuvable")
    })
    public ResponseEntity<ApiResponseDTO<Void>> delete(@Parameter(description = "Identifiant de la consommation", example = "1") @PathVariable Long id) {
        consommationService.delete(id);
        return ResponseEntity.ok(ApiResponseDTO.ok("Supprimee avec succes", null));
    }
}



