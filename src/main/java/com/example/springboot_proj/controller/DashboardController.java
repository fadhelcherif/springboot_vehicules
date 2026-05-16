package com.example.springboot_proj.controller;

import com.example.springboot_proj.dto.ApiResponseDTO;
import com.example.springboot_proj.dto.FuelCostByVehiculeResponse;
import com.example.springboot_proj.dto.VehiculeActivityResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.springboot_proj.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard", description = "Indicateurs de cout carburant et d'utilisation de la flotte")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/ping")
    @Operation(summary = "Ping dashboard", description = "Verifie rapidement que le controller dashboard est disponible")
    @ApiResponse(responseCode = "200", description = "Controller disponible")
    public ResponseEntity<ApiResponseDTO<String>> ping() {
        return ResponseEntity.ok(ApiResponseDTO.ok("dashboard-ok"));
    }

    @GetMapping("/fuel-costs")
    @Operation(summary = "Cout carburant par vehicule", description = "Retourne le cout total de carburant agrege par vehicule")
    @ApiResponse(responseCode = "200", description = "Indicateurs carburant", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FuelCostByVehiculeResponse.class))))
    public ResponseEntity<ApiResponseDTO<List<FuelCostByVehiculeResponse>>> getFuelCostsByVehicule() {
        return ResponseEntity.ok(ApiResponseDTO.ok(dashboardService.getFuelCostsByVehicule()));
    }

    @GetMapping("/vehicules/most-active")
    @Operation(summary = "Vehicules les plus actifs", description = "Classe les vehicules par nombre de missions et distance totale")
    @ApiResponse(responseCode = "200", description = "Classement d'activite", content = @Content(array = @ArraySchema(schema = @Schema(implementation = VehiculeActivityResponse.class))))
    public ResponseEntity<ApiResponseDTO<List<VehiculeActivityResponse>>> getMostActiveVehicules(
            @Parameter(description = "Nombre maximum de vehicules retournes (defaut: 5)", example = "5")
            @RequestParam(required = false) Integer limit) {
        return ResponseEntity.ok(ApiResponseDTO.ok(dashboardService.getMostActiveVehicules(limit)));
    }
}

