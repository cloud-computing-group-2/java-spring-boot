package com.micro.managertravel.pilot.domain;


import com.micro.managertravel.pilot.dto.PilotRequestDto;
import com.micro.managertravel.pilot.dto.PilotResponseDto;
import com.micro.managertravel.pilot.infraestructure.PilotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PilotService {

    @Autowired
    private PilotRepository pilotRepository;

    private final ModelMapper modelMapper;

    public PilotService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PilotResponseDto createPilot(PilotRequestDto pilotRequestDto) {
        Pilot pilot =  modelMapper.map(pilotRequestDto, Pilot.class);
        Pilot savedPilot = pilotRepository.save(pilot);
        PilotResponseDto createdPilot = modelMapper.map(savedPilot, PilotResponseDto.class);
        return createdPilot;
    }

    public PilotResponseDto getPilotById(Long pilotId) {
        Pilot pilot = pilotRepository.findById(pilotId).orElse(null);
        PilotResponseDto createdPilot = modelMapper.map(pilot, PilotResponseDto.class);
        return createdPilot;
    }

    public List<PilotResponseDto> getAllPilots() {
        List<Pilot> pilots = pilotRepository.findAll();
        List<PilotResponseDto> pilotResponseDtos = new ArrayList<>();
        for (Pilot pilot : pilots) {
            PilotResponseDto pilotResponseDto = modelMapper.map(pilot, PilotResponseDto.class);
            pilotResponseDtos.add(pilotResponseDto);
        }
        return pilotResponseDtos;
    }

    public PilotResponseDto updatePilotById(Long pilotId, PilotRequestDto pilotRequestDto) {
        Pilot pilot = pilotRepository.findById(pilotId).orElse(null);

        pilot.setName(pilotRequestDto.getName());
        pilot.setBirthDate(pilotRequestDto.getBirthDate());
        pilot.setGender(pilotRequestDto.getGender());

        Pilot updatedPilot = pilotRepository.save(pilot);
        PilotResponseDto updatedPilotResponse = modelMapper.map(updatedPilot, PilotResponseDto.class);

        return updatedPilotResponse;
    }

    public void deletePilotById(Long pilotId) {
        Pilot pilot = pilotRepository.findById(pilotId).orElse(null);
        pilotRepository.deleteById(pilotId);
    }
}
