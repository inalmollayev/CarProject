package org.car.car.controller;

import lombok.RequiredArgsConstructor;
import org.car.car.dto.CarDto;
import org.car.car.model.Car;
import org.car.car.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/get")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("add")
    public CarDto addCar(@RequestBody CarDto carDTO) {
        return carService.createCar(carDTO);
    }

    @GetMapping("/id/{id}")
    public CarDto getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @PutMapping("/updateCar/{id}")
    public CarDto updateCar(@PathVariable Long id, @RequestBody CarDto carDTO) {
        return carService.updateCar(id, carDTO);
    }

    @DeleteMapping("/deleted/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @GetMapping("getYear/{year}")
    public List<CarDto> getCarsByYear(@PathVariable int year) {
        return carService.getCarsByYear(year);
    }
}
