package com.micro.managertravel.pilot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PilotResponseDto {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String gender;
    @NotNull
    private LocalDate birthDate;
}
