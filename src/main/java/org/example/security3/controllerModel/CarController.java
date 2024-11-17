package org.example.security3.controllerModel;


import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.security3.dto.CarDto;
import org.example.security3.handler.CarNotFoundException;
import org.example.security3.models.Car;
import org.example.security3.servicee.CarService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CarController {

    private final CarService carService;


    @GetMapping("/cars")
    public List<Car> allCars(){
        return carService.find();
    }
    @PostMapping("/save")
    public void save(@RequestBody @Valid CarDto carDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CarNotFoundException(bindingResult.getFieldError().getField());
        }
        log.error("ОШИБКИ НЕТ В МЕТОД SAVE CARS");
        carService.save(carDto);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable int id){
        carService.delete(id);
    }
}
