package com.micro.managertravel.pilot.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PilotRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String gender;
    @NotNull
    private LocalDate birthDate;
}
