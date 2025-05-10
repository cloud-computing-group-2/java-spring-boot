package com.micro.managertravel.flight.domain;

import com.micro.managertravel.pilot.domain.Pilot;
import com.micro.managertravel.plane.domain.Plane;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="plane_id", nullable=false)
    private Plane plane;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="pilot_id", nullable=false)
    private Pilot pilot;

    @Column(nullable = false)
    private String origin;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private LocalDateTime departure;
    @Column(nullable = false)
    private LocalDateTime arrival;

    public Flight(
            String origin,
            String destination,
            LocalDateTime departure,
            LocalDateTime arrival,
            Plane plane,
            Pilot pilot
    ) {
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
        this.plane = plane;
        this.pilot = pilot;
    }

}
