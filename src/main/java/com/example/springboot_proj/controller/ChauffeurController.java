package com.example.springboot_proj.controller;

import com.example.springboot_proj.dto.ApiResponseDTO;
import com.example.springboot_proj.dto.ChauffeurDTO;
import com.example.springboot_proj.service.ChauffeurService;
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
@RequestMapping("/api/chauffeurs")
@Tag(name = "Chauffeurs", description = "CRUD des chauffeurs de la flotte")
public class ChauffeurController {

    @Autowired
    private ChauffeurService chauffeurService;

    @PostMapping
    @Operation(summary = "Creer un chauffeur", description = "Ajoute un nouveau chauffeur")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Chauffeur cree", content = @Content(schema = @Schema(implementation = ChauffeurDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requete invalide")
    })
    public ResponseEntity<ApiResponseDTO<ChauffeurDTO>> create(@Valid @RequestBody ChauffeurDTO dto) {
        ChauffeurDTO created = chauffeurService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDTO.ok("Chauffeur cree avec succes", created));
    }

    @GetMapping
    @Operation(summary = "Lister les chauffeurs", description = "Retourne tous les chauffeurs")
    @ApiResponse(responseCode = "200", description = "Liste des chauffeurs", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ChauffeurDTO.class))))
    public ResponseEntity<ApiResponseDTO<List<ChauffeurDTO>>> getAll() {
        return ResponseEntity.ok(ApiResponseDTO.ok(chauffeurService.getAll()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperer un chauffeur", description = "Retourne un chauffeur par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chauffeur trouve", content = @Content(schema = @Schema(implementation = ChauffeurDTO.class))),
            @ApiResponse(responseCode = "404", description = "Chauffeur introuvable")
    })
    public ResponseEntity<ApiResponseDTO<ChauffeurDTO>> getById(@Parameter(description = "Identifiant du chauffeur", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponseDTO.ok(chauffeurService.getById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre a jour un chauffeur", description = "Modifie les informations d'un chauffeur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chauffeur mis a jour", content = @Content(schema = @Schema(implementation = ChauffeurDTO.class))),
            @ApiResponse(responseCode = "404", description = "Chauffeur introuvable")
    })
    public ResponseEntity<ApiResponseDTO<ChauffeurDTO>> update(@Parameter(description = "Identifiant du chauffeur", example = "1") @PathVariable Long id, @Valid @RequestBody ChauffeurDTO dto) {
        return ResponseEntity.ok(ApiResponseDTO.ok("Mis a jour", chauffeurService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un chauffeur", description = "Supprime un chauffeur par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chauffeur supprime"),
            @ApiResponse(responseCode = "404", description = "Chauffeur introuvable")
    })
    public ResponseEntity<ApiResponseDTO<Void>> delete(@Parameter(description = "Identifiant du chauffeur", example = "1") @PathVariable Long id) {
        chauffeurService.delete(id);
        return ResponseEntity.ok(ApiResponseDTO.ok("Supprime avec succes", null));
    }

    @PostMapping(value = "/{id}/image", consumes = "multipart/form-data")
    @Operation(
        summary = "Uploader une image pour le chauffeur",
        description = "Permet de charger une image (photo de profil) pour un chauffeur existant."
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Image uploadee avec succes",
                content = @Content(schema = @Schema(implementation = ChauffeurDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Chauffeur introuvable"),
            @ApiResponse(responseCode = "400", description = "Fichier invalide")
    })
    public ResponseEntity<ApiResponseDTO<ChauffeurDTO>> uploadImage(
            @Parameter(description = "ID du chauffeur", example = "1")
            @PathVariable Long id,
            @Parameter(
                description = "Fichier image",
                content = @Content(mediaType = "application/octet-stream", schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart("file") MultipartFile file) throws IOException {
        String imageData = Base64.getEncoder().encodeToString(file.getBytes());
        return ResponseEntity.ok(ApiResponseDTO.ok("Image uploadee", chauffeurService.updateImage(id, imageData)));
    }
}

