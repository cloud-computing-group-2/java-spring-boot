package com.micro.managertravel.flight.domain;

import com.micro.managertravel.flight.dto.FlightRequestDto;
import com.micro.managertravel.flight.dto.FlightResponseDto;
import com.micro.managertravel.flight.infraestructure.FlightRepository;
import com.micro.managertravel.plane.domain.Plane;
import com.micro.managertravel.plane.dto.PlaneResponseDto;
import com.micro.managertravel.plane.infraestructure.PlaneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public FlightService(ModelMapper modelMapper, FlightRepository flightRepository) {
        this.modelMapper = modelMapper;
    }

    public FlightResponseDto createFlight(FlightRequestDto flightRequestDto) {
        // fix: can create flights w the same plane at th same date

        Plane plane = planeRepository.findById(flightRequestDto.getIdPlane()).get();

        Flight flight = new Flight();
        flight.setDeparture(flightRequestDto.getDeparture());
        flight.setDestination(flightRequestDto.getDestination());
        flight.setArrival(flightRequestDto.getArrival());
        flight.setOrigin(flightRequestDto.getOrigin());
        flight.setPlane(plane);

        Flight savedFlight = flightRepository.save(flight);
        FlightResponseDto flightResponseDto = modelMapper.map(savedFlight, FlightResponseDto.class);
        PlaneResponseDto planeResponseDto = modelMapper.map(plane, PlaneResponseDto.class);
        flightResponseDto.setPlaneDto(planeResponseDto);

        return flightResponseDto;
    }

    public List<FlightResponseDto> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        List<FlightResponseDto> flightResponseDtos = new ArrayList<>();
        for (Flight flight : flights) {
            FlightResponseDto flightResponseDto = modelMapper.map(flight, FlightResponseDto.class);
            PlaneResponseDto planeResponseDto = modelMapper.map(flight.getPlane(), PlaneResponseDto.class);
            flightResponseDto.setPlaneDto(planeResponseDto);
            flightResponseDtos.add(flightResponseDto);
        }
        return flightResponseDtos;
    }

    public FlightResponseDto getFlightById(Long id) {
        Flight flight = flightRepository.findById(id).orElse(null);
        FlightResponseDto flightResponseDto = modelMapper.map(flight, FlightResponseDto.class);
        return flightResponseDto;
    }

    public FlightResponseDto updateFlight(Long id, FlightRequestDto flightRequestDto) {
        Flight flight = flightRepository.findById(id).orElse(null);
        flight.setArrival(flightRequestDto.getArrival());
        flight.setDeparture(flightRequestDto.getDeparture());
        flight.setDestination(flightRequestDto.getDestination());
        flight.setOrigin(flightRequestDto.getOrigin());

        Plane plane = planeRepository.findById(flightRequestDto.getIdPlane()).orElse(null);
        flight.setPlane(plane);

        flightRepository.save(flight);
        FlightResponseDto flightResponseDto = modelMapper.map(flight, FlightResponseDto.class);
        return flightResponseDto;

    }

    public void deleteFlightById(Long id) {

    }

}
