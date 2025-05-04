package com.micro.managertravel.plane.domain;

import com.micro.managertravel.plane.dto.PlaneRequestDto;
import com.micro.managertravel.plane.dto.PlaneResponseDto;
import com.micro.managertravel.plane.infraestructure.PlaneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaneService {

    @Autowired
    private PlaneRepository planeRepository;

    private final ModelMapper modelMapper;

    public PlaneService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PlaneResponseDto createPlane(PlaneRequestDto planeRequestDto) {
        Plane plane =  modelMapper.map(planeRequestDto, Plane.class);
        Plane savedPlane = planeRepository.save(plane);
        PlaneResponseDto createdPlane = modelMapper.map(savedPlane, PlaneResponseDto.class);
        return createdPlane;
    }

    public PlaneResponseDto getPlaneById(Long planeId) {
        Plane plane = planeRepository.findById(planeId).orElse(null);
        PlaneResponseDto createdPlane = modelMapper.map(plane, PlaneResponseDto.class);
        return createdPlane;
    }

    public List<PlaneResponseDto> getAllPlanes() {
        List<Plane> planes = planeRepository.findAll();
        List<PlaneResponseDto> planeResponseDtos = new ArrayList<>();
        for (Plane plane : planes) {
            PlaneResponseDto planeResponseDto = modelMapper.map(plane, PlaneResponseDto.class);
            planeResponseDtos.add(planeResponseDto);
        }
        return planeResponseDtos;
    }

    public PlaneResponseDto updatePlaneById(Long planeId, PlaneRequestDto planeRequestDto) {
        Plane plane = planeRepository.findById(planeId).orElse(null);

        plane.setModel(planeRequestDto.getModel());
        plane.setYear_manufacture(planeRequestDto.getYear_manufacture());
        plane.setCapacity(planeRequestDto.getCapacity());

        Plane updatedPlane = planeRepository.save(plane);
        PlaneResponseDto updatedPlaneResponse = modelMapper.map(updatedPlane, PlaneResponseDto.class);

        return updatedPlaneResponse;
    }

    public void deletePlaneById(Long planeId) {
        Plane plane = planeRepository.findById(planeId).orElse(null);
        planeRepository.deleteById(planeId);
    }

}
