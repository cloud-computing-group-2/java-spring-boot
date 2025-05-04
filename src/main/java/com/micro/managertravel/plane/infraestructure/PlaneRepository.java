package com.micro.managertravel.plane.infraestructure;

import com.micro.managertravel.plane.domain.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Long> {

}
