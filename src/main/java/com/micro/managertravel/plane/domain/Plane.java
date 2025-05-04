package com.micro.managertravel.plane.domain;

import com.micro.managertravel.flight.domain.Flight;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@NoArgsConstructor
@Getter
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "plane", fetch = FetchType.EAGER)
    private List<Flight> flights;

    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private Integer capacity;
    @Column(nullable = false)
    private Year year_manufacture;

    public Plane(
            String model,
            Integer capacity,
            Year year_manufacture
    ) {
        this.model = model;
        this.capacity = capacity;
        this.year_manufacture = year_manufacture;
        this.flights = new ArrayList<>();
    }

}
