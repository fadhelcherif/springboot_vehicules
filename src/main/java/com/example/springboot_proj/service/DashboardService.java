package com.example.springboot_proj.service;

import com.example.springboot_proj.dto.FuelCostByVehiculeResponse;
import com.example.springboot_proj.dto.VehiculeActivityResponse;
import com.example.springboot_proj.repository.ConsommationRepository;
import com.example.springboot_proj.repository.MissionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DashboardService {

    private final ConsommationRepository consommationRepository;
    private final MissionRepository missionRepository;

    public DashboardService(ConsommationRepository consommationRepository, MissionRepository missionRepository) {
        this.consommationRepository = consommationRepository;
        this.missionRepository = missionRepository;
    }

    public List<FuelCostByVehiculeResponse> getFuelCostsByVehicule() {
        return consommationRepository.getFuelCostDashboard()
                .stream()
                .map(item -> new FuelCostByVehiculeResponse(
                        item.getVehiculeId(),
                        item.getImmatriculation(),
                        item.getCoutTotalCarburant()
                ))
                .toList();
    }

    public List<VehiculeActivityResponse> getMostActiveVehicules(Integer limit) {
        int pageSize = (limit == null || limit <= 0) ? 5 : limit;
        return missionRepository.findVehiculeActivity(PageRequest.of(0, pageSize));
    }
}

