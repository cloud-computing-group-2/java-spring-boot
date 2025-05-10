package com.micro.managertravel.pilot.domain;


import com.micro.managertravel.flight.domain.Flight;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@NoArgsConstructor
@Getter
public class Pilot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "pilot", fetch = FetchType.EAGER)
    private List<Flight> flights;

    public Pilot(
            String name,
            String gender,
            LocalDate birthDate
    ) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.flights = new ArrayList<>();
    }



}
