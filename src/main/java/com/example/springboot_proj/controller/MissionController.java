package com.example.springboot_proj.controller;

import com.example.springboot_proj.dto.ApiResponseDTO;
import com.example.springboot_proj.dto.MissionDTO;
import com.example.springboot_proj.service.MissionService;
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
@RequestMapping("/api/missions")
@Tag(name = "Missions", description = "CRUD des missions et affectation chauffeur-vehicule")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @PostMapping
    @Operation(summary = "Creer une mission", description = "Cree une mission avec affectation initiale d'un vehicule et d'un chauffeur")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Mission creee", content = @Content(schema = @Schema(implementation = MissionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Vehicule ou chauffeur introuvable")
    })
    public ResponseEntity<ApiResponseDTO<MissionDTO>> create(@Valid @RequestBody MissionDTO dto) {
        MissionDTO created = missionService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDTO.ok("Mission creee avec succes", created));
    }

    @GetMapping
    @Operation(summary = "Lister les missions", description = "Retourne toutes les missions")
    @ApiResponse(responseCode = "200", description = "Liste des missions", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MissionDTO.class))))
    public ResponseEntity<ApiResponseDTO<List<MissionDTO>>> getAll() {
        return ResponseEntity.ok(ApiResponseDTO.ok(missionService.getAll()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperer une mission", description = "Retourne une mission par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mission trouvee", content = @Content(schema = @Schema(implementation = MissionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Mission introuvable")
    })
    public ResponseEntity<ApiResponseDTO<MissionDTO>> getById(@Parameter(description = "Identifiant de la mission", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponseDTO.ok(missionService.getById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre a jour une mission", description = "Met a jour les details d'une mission")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mission mise a jour", content = @Content(schema = @Schema(implementation = MissionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Mission, vehicule ou chauffeur introuvable")
    })
    public ResponseEntity<ApiResponseDTO<MissionDTO>> update(@Parameter(description = "Identifiant de la mission", example = "1") @PathVariable Long id, @Valid @RequestBody MissionDTO dto) {
        return ResponseEntity.ok(ApiResponseDTO.ok("Mis a jour", missionService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une mission", description = "Supprime une mission par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mission supprimee"),
            @ApiResponse(responseCode = "404", description = "Mission introuvable")
    })
    public ResponseEntity<ApiResponseDTO<Void>> delete(@Parameter(description = "Identifiant de la mission", example = "1") @PathVariable Long id) {
        missionService.delete(id);
        return ResponseEntity.ok(ApiResponseDTO.ok("Supprimee avec succes", null));
    }
}



