package com.example.springboot_proj.controller;

import com.example.springboot_proj.dto.FuelCostByVehiculeResponse;
import com.example.springboot_proj.dto.VehiculeActivityResponse;
import com.example.springboot_proj.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/fuel-costs")
    public List<FuelCostByVehiculeResponse> getFuelCostsByVehicule() {
        return dashboardService.getFuelCostsByVehicule();
    }

    @GetMapping("/vehicules/most-active")
    public List<VehiculeActivityResponse> getMostActiveVehicules(@RequestParam(required = false) Integer limit) {
        return dashboardService.getMostActiveVehicules(limit);
    }
}

