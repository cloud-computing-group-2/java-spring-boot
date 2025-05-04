package com.micro.managertravel.plane.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Setter
@Getter
public class PlaneRequestDto {
    @NotNull
    private String model;
    @NotNull
    private Integer capacity;
    @NotNull
    private Year year_manufacture;
}
