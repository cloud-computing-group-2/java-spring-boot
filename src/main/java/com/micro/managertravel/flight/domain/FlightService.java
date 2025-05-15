package com.micro.managertravel.flight.domain;

import com.micro.managertravel.flight.dto.FlightRequestDto;
import com.micro.managertravel.flight.dto.FlightResponseDto;
import com.micro.managertravel.flight.infraestructure.FlightRepository;
import com.micro.managertravel.pilot.domain.Pilot;
import com.micro.managertravel.pilot.dto.PilotResponseDto;
import com.micro.managertravel.pilot.infraestructure.PilotRepository;
import com.micro.managertravel.plane.domain.Plane;
import com.micro.managertravel.plane.dto.PlaneResponseDto;
import com.micro.managertravel.plane.infraestructure.PlaneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    private final ModelMapper modelMapper;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    public PlaneRepository planeRepository;
    @Autowired
    public PilotRepository pilotRepository;

    public FlightService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FlightResponseDto createFlight(FlightRequestDto flightRequestDto) {
        // fix: can create flights w the same plane at th same date

        Plane plane = planeRepository.findById(flightRequestDto.getIdPlane()).orElseThrow();
        Pilot pilot = pilotRepository.findById(flightRequestDto.getIdPilot()).orElseThrow();

        Flight flight = new Flight();
        flight.setDeparture(flightRequestDto.getDeparture());
        flight.setDestination(flightRequestDto.getDestination());
        flight.setArrival(flightRequestDto.getArrival());
        flight.setOrigin(flightRequestDto.getOrigin());
        flight.setPlane(plane);
        flight.setPilot(pilot);

        Flight savedFlight = flightRepository.save(flight);
        FlightResponseDto flightResponseDto = modelMapper.map(savedFlight, FlightResponseDto.class);

        PlaneResponseDto planeResponseDto = modelMapper.map(plane, PlaneResponseDto.class);
        PilotResponseDto pilotResponseDto = modelMapper.map(pilot, PilotResponseDto.class);

        flightResponseDto.setPlaneDto(planeResponseDto);
        flightResponseDto.setPilotDto(pilotResponseDto);

        return flightResponseDto;
    }

    public List<FlightResponseDto> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        List<FlightResponseDto> flightResponseDtos = new ArrayList<>();
        for (Flight flight : flights) {
            FlightResponseDto flightResponseDto = modelMapper.map(flight, FlightResponseDto.class);
            PlaneResponseDto planeResponseDto = modelMapper.map(flight.getPlane(), PlaneResponseDto.class);
            PilotResponseDto pilotResponseDto = modelMapper.map(flight.getPilot(), PilotResponseDto.class);

            flightResponseDto.setPlaneDto(planeResponseDto);
            flightResponseDto.setPilotDto(pilotResponseDto);

            flightResponseDtos.add(flightResponseDto);
        }
        return flightResponseDtos;
    }

    public FlightResponseDto getFlightById(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow();
        FlightResponseDto flightResponseDto = modelMapper.map(flight, FlightResponseDto.class);
        return flightResponseDto;
    }

    public FlightResponseDto updateFlight(Long id, FlightRequestDto flightRequestDto) {
        Flight flight = flightRepository.findById(id).orElseThrow();
        flight.setArrival(flightRequestDto.getArrival());
        flight.setDeparture(flightRequestDto.getDeparture());
        flight.setDestination(flightRequestDto.getDestination());
        flight.setOrigin(flightRequestDto.getOrigin());

        Plane plane = planeRepository.findById(flightRequestDto.getIdPlane()).orElseThrow();
        Pilot pilot = pilotRepository.findById(flightRequestDto.getIdPilot()).orElseThrow();
        flight.setPlane(plane);
        flight.setPilot(pilot);

        flightRepository.save(flight);
        FlightResponseDto flightResponseDto = modelMapper.map(flight, FlightResponseDto.class);
        return flightResponseDto;

    }

    public Page<FlightResponseDto> getFlightsPaged(Pageable pageable) {

        Page<Flight> flights = flightRepository.findAll(pageable);

        return flights.map(flight -> {
            FlightResponseDto flightResponseDto = modelMapper.map(flight, FlightResponseDto.class);
            PlaneResponseDto planeResponseDto = modelMapper.map(flight.getPlane(), PlaneResponseDto.class);
            PilotResponseDto pilotResponseDto = modelMapper.map(flight.getPilot(), PilotResponseDto.class);

            flightResponseDto.setPlaneDto(planeResponseDto);
            flightResponseDto.setPilotDto(pilotResponseDto);

            return flightResponseDto;
        });
    }

    public void deleteFlightById(Long id) {

    }

}
