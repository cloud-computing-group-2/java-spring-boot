package com.micro.managertravel.plane.application;

import com.micro.managertravel.plane.domain.PlaneService;
import com.micro.managertravel.plane.dto.PlaneRequestDto;
import com.micro.managertravel.plane.dto.PlaneResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/plane")
public class PlaneController {

    @Autowired
    private PlaneService planeService;

    @PostMapping()
    public ResponseEntity<PlaneResponseDto> createPlane(@RequestBody PlaneRequestDto planeRequestDto) {
        PlaneResponseDto createdPlane = planeService.createPlane(planeRequestDto);
        URI location = URI.create("/plane/" + createdPlane.getId());
        return ResponseEntity.created(location).body(createdPlane);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlaneResponseDto>> getAllPlanes() {
        List<PlaneResponseDto> planes = planeService.getAllPlanes();
        return ResponseEntity.ok(planes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaneResponseDto> getPlaneById(@PathVariable Long id) {
        return ResponseEntity.ok(planeService.getPlaneById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaneResponseDto> updatePlane(@PathVariable Long id, @RequestBody PlaneRequestDto planeRequestDto) {
        return ResponseEntity.ok(planeService.updatePlaneById(id, planeRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlane(@PathVariable Long id) {
        planeService.deletePlaneById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<PlaneResponseDto>> getPlanesPaged(Pageable pageable) {
        Page<PlaneResponseDto> page = planeService.getPlanesPaged(pageable);
        return ResponseEntity.ok(page);
    }

}
