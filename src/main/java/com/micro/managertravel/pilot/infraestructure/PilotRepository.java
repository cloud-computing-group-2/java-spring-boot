package com.micro.managertravel.pilot.infraestructure;

import com.micro.managertravel.pilot.domain.Pilot;
import com.micro.managertravel.plane.domain.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotRepository extends JpaRepository<Pilot, Long> {
}
