package com.example.springboot_proj.controller;

import com.example.springboot_proj.dto.ChauffeurRequest;
import com.example.springboot_proj.dto.ChauffeurResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Chauffeurs", description = "CRUD des chauffeurs de la flotte")
public class ChauffeurController {

    private final ChauffeurService chauffeurService;

    public ChauffeurController(ChauffeurService chauffeurService) {
        this.chauffeurService = chauffeurService;
    }

    @PostMapping
    @Operation(summary = "Creer un chauffeur", description = "Ajoute un nouveau chauffeur")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Chauffeur cree", content = @Content(schema = @Schema(implementation = ChauffeurResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requete invalide")
    })
    public ResponseEntity<ChauffeurResponse> create(@RequestBody ChauffeurRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chauffeurService.create(request));
    }

    @GetMapping
    @Operation(summary = "Lister les chauffeurs", description = "Retourne tous les chauffeurs")
    @ApiResponse(responseCode = "200", description = "Liste des chauffeurs", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ChauffeurResponse.class))))
    public List<ChauffeurResponse> getAll() {
        return chauffeurService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperer un chauffeur", description = "Retourne un chauffeur par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chauffeur trouve", content = @Content(schema = @Schema(implementation = ChauffeurResponse.class))),
            @ApiResponse(responseCode = "404", description = "Chauffeur introuvable")
    })
    public ChauffeurResponse getById(@Parameter(description = "Identifiant du chauffeur", example = "1") @PathVariable Long id) {
        return chauffeurService.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre a jour un chauffeur", description = "Modifie les informations d'un chauffeur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chauffeur mis a jour", content = @Content(schema = @Schema(implementation = ChauffeurResponse.class))),
            @ApiResponse(responseCode = "404", description = "Chauffeur introuvable")
    })
    public ChauffeurResponse update(@Parameter(description = "Identifiant du chauffeur", example = "1") @PathVariable Long id, @RequestBody ChauffeurRequest request) {
        return chauffeurService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un chauffeur", description = "Supprime un chauffeur par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Chauffeur supprime"),
            @ApiResponse(responseCode = "404", description = "Chauffeur introuvable")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Identifiant du chauffeur", example = "1") @PathVariable Long id) {
        chauffeurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

