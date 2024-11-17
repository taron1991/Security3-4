package org.example.security3.repos;

import org.example.security3.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car,Integer> {
}
