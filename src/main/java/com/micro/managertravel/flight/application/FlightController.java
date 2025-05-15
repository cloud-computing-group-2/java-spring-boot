package com.micro.managertravel.flight.application;

import com.micro.managertravel.flight.domain.Flight;
import com.micro.managertravel.flight.domain.FlightService;
import com.micro.managertravel.flight.dto.FlightRequestDto;
import com.micro.managertravel.flight.dto.FlightResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping()
    public ResponseEntity<FlightResponseDto> createFlight(@RequestBody FlightRequestDto flight) {
        FlightResponseDto createdFlight = flightService.createFlight(flight);
        URI location = URI.create("/flight/" + createdFlight.getId());
        return ResponseEntity.created(location).body(createdFlight);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FlightResponseDto>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDto> getFlightById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponseDto> updateFlight(@PathVariable Long id, @RequestBody FlightRequestDto flight) {
        return ResponseEntity.ok(flightService.updateFlight(id, flight));
    }

    // Eliminar un vuelo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlightById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<FlightResponseDto>> getFlightsPaged(Pageable pageable) {
        Page<FlightResponseDto> page = flightService.getFlightsPaged(pageable);
        return ResponseEntity.ok(page);
    }

}
