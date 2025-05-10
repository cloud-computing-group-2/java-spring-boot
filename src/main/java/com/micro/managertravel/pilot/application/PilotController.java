package com.micro.managertravel.pilot.application;

import com.micro.managertravel.pilot.domain.PilotService;

import com.micro.managertravel.pilot.dto.PilotRequestDto;
import com.micro.managertravel.pilot.dto.PilotResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pilot")
public class PilotController {
    @Autowired
    private PilotService pilotService;

    @PostMapping()
    public ResponseEntity<PilotResponseDto> createPilot(@RequestBody PilotRequestDto planeRequestDto) {
        PilotResponseDto createdPlane = pilotService.createPilot(planeRequestDto);
        URI location = URI.create("/plane/" + createdPlane.getId());
        return ResponseEntity.created(location).body(createdPlane);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PilotResponseDto>> getAllPilots() {
        List<PilotResponseDto> planes = pilotService.getAllPilots();
        return ResponseEntity.ok(planes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PilotResponseDto> getPilotById(@PathVariable Long id) {
        return ResponseEntity.ok(pilotService.getPilotById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PilotResponseDto> updatePilot(@PathVariable Long id, @RequestBody PilotRequestDto planeRequestDto) {
        return ResponseEntity.ok(pilotService.updatePilotById(id, planeRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePilot(@PathVariable Long id) {
        pilotService.deletePilotById(id);
        return ResponseEntity.noContent().build();
    }

}

// docker run --name postgres-db -e POSTGRES_PASSWORD=postgres -p 5433:5432 -d postgres

