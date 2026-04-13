package com.example.springboot_proj.controller;

import com.example.springboot_proj.dto.AffectationRequest;
import com.example.springboot_proj.dto.MissionRequest;
import com.example.springboot_proj.dto.MissionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Missions", description = "CRUD des missions et affectation chauffeur-vehicule")
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping
    @Operation(summary = "Creer une mission", description = "Cree une mission avec affectation initiale d'un vehicule et d'un chauffeur")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Mission creee", content = @Content(schema = @Schema(implementation = MissionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Vehicule ou chauffeur introuvable")
    })
    public ResponseEntity<MissionResponse> create(@RequestBody MissionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(missionService.create(request));
    }

    @GetMapping
    @Operation(summary = "Lister les missions", description = "Retourne toutes les missions")
    @ApiResponse(responseCode = "200", description = "Liste des missions", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MissionResponse.class))))
    public List<MissionResponse> getAll() {
        return missionService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperer une mission", description = "Retourne une mission par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mission trouvee", content = @Content(schema = @Schema(implementation = MissionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Mission introuvable")
    })
    public MissionResponse getById(@Parameter(description = "Identifiant de la mission", example = "1") @PathVariable Long id) {
        return missionService.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre a jour une mission", description = "Met a jour les details d'une mission")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mission mise a jour", content = @Content(schema = @Schema(implementation = MissionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Mission, vehicule ou chauffeur introuvable")
    })
    public MissionResponse update(@Parameter(description = "Identifiant de la mission", example = "1") @PathVariable Long id, @RequestBody MissionRequest request) {
        return missionService.update(id, request);
    }

    @PutMapping("/{id}/affectation")
    @Operation(summary = "Mettre a jour une affectation", description = "Change le vehicule et/ou le chauffeur affecte a une mission")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Affectation mise a jour", content = @Content(schema = @Schema(implementation = MissionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Mission, vehicule ou chauffeur introuvable")
    })
    public MissionResponse updateAffectation(@Parameter(description = "Identifiant de la mission", example = "1") @PathVariable Long id, @RequestBody AffectationRequest request) {
        return missionService.updateAffectation(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une mission", description = "Supprime une mission par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Mission supprimee"),
            @ApiResponse(responseCode = "404", description = "Mission introuvable")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Identifiant de la mission", example = "1") @PathVariable Long id) {
        missionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

