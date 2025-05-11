package com.micro.managertravel.fakedata;
import com.github.javafaker.Faker;
import com.micro.managertravel.flight.domain.Flight;
import com.micro.managertravel.flight.infraestructure.FlightRepository;
import com.micro.managertravel.pilot.domain.Pilot;
import com.micro.managertravel.pilot.infraestructure.PilotRepository;
import com.micro.managertravel.plane.domain.Plane;
import com.micro.managertravel.plane.infraestructure.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataLoader {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private PlaneRepository planeRepository;  // Para manejar los aviones
    @Autowired
    private PilotRepository pilotRepository;  // Para manejar los pilotos

    private final Faker faker = new Faker();

    public void loadPilotsData() {
        List<Pilot> pilots = new ArrayList<>();

        for (int i = 0; i < 20000; i++) {

            String name = faker.name().fullName();
            String gender = faker.options().option("Male", "Female");  // Generar género de forma aleatoria
            LocalDate birthDate = faker.date().birthday(25, 60).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(); // Edad entre 25 y 60 años

            Pilot pilot = new Pilot(name, gender, birthDate);
            pilots.add(pilot);
        }

        pilotRepository.saveAll(pilots);
    }

    public void loadPlanesData() {
        List<Plane> planes = new ArrayList<>();

        for (int i = 0; i < 20000; i++) {
            String model = faker.company().name();
            Integer capacity = faker.number().numberBetween(100, 400);
            Year year_manufacture = Year.of(faker.number().numberBetween(1990, 2023));
            Plane plane = new Plane(model, capacity, year_manufacture);
            planes.add(plane);
        }
        planeRepository.saveAll(planes);
    }

    public void loadFlightsData() {

        loadPilotsData();
        loadPlanesData();

        List<Flight> flights = new ArrayList<>();

        List<Plane> planes = planeRepository.findAll();
        List<Pilot> pilotos = pilotRepository.findAll();

        for (int i = 0; i < 20000; i++) {
            String origin = faker.address().city();
            String destination = faker.address().city();
            LocalDateTime departure = faker.date().future(10, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime arrival = departure.plusHours(faker.number().numberBetween(2, 8));

            Plane plane = planes.get(faker.number().numberBetween(0, planes.size()));  // Seleccionar un avión aleatorio
            Pilot pilot = pilotos.get(faker.number().numberBetween(0, pilotos.size()));  // Seleccionar un piloto aleatorio

            Flight flight = new Flight(origin, destination, departure, arrival, plane, pilot);
            flights.add(flight);
        }

        flightRepository.saveAll(flights);
    }
}
