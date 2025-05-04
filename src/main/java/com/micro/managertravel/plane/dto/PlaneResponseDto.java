package com.micro.managertravel.plane.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
public class PlaneResponseDto {
    @NotNull
    private Long id;
    @NotNull
    private String model;
    @NotNull
    private Integer capacity;
    @NotNull
    private Year year_manufacture;
}
