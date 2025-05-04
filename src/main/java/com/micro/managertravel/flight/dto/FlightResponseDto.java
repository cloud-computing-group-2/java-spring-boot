package com.micro.managertravel.flight.dto;

import com.micro.managertravel.plane.dto.PlaneRequestDto;
import com.micro.managertravel.plane.dto.PlaneResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class FlightResponseDto {
    @NotNull
    private Long id;
    @NotNull
    @Valid
    private PlaneResponseDto planeDto;

    @NotNull
    private String origin;

    @NotNull
    private String destination;
    @NotNull
    private LocalDateTime departure;
    @NotNull
    private LocalDateTime arrival;

}
