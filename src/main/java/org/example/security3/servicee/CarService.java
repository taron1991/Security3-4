package org.example.security3.servicee;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.security3.dto.CarDto;
import org.example.security3.models.Car;
import org.example.security3.repos.CarRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepo carRepo;
    private final ObjectMapper objectMapper;

    public List<Car> find() {
        return carRepo.findAll();
    }

    @Transactional
    public void save(CarDto carDto) {
        Car car = objectMapper.convertValue(carDto, Car.class);
        carRepo.save(car);

    }

    @Transactional
    public void delete(int id) {
        carRepo.deleteById(id);
    }
}
