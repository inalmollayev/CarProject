package org.car.car.service;

import lombok.RequiredArgsConstructor;
import org.car.car.dto.CarDto;
import org.car.car.model.Brand;
import org.car.car.model.Car;
import org.car.car.repository.BrandRepository;
import org.car.car.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    public CarDto createCar(CarDto carDto) {
        Brand brandByName = brandRepository.findByName(carDto.getBrandName());
        Car carByModel = carRepository.findCarByModel(carDto.getModel());

        if (brandByName != null && carByModel == null) {
            Car map = modelMapper.map(carDto, Car.class);
            Car save = carRepository.save(map);
            return modelMapper.map(save, CarDto.class);
        }

        return new CarDto();
    }

    public List<Car> getAllCars() {
        return carRepository.findAll().stream().map(car -> modelMapper.map(car, Car.class)).toList();
    }


    public CarDto getCarById(Long id) {
        return modelMapper.map(carRepository.findById(id), CarDto.class);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public CarDto updateCar(Long id, CarDto carDTO) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        Brand brand = brandRepository.findByName(carDTO.getBrandName());
        if (brand == null) {
            throw new RuntimeException("Brand not found");
        }
        car.setModel(carDTO.getModel());
        car.setProudYear(carDTO.getYear());
        car.setBrand(brand);
        return modelMapper.map(carRepository.save(car), CarDto.class);
    }

    public List<CarDto> getCarsByYear(int year) {
        return carRepository.findByProudYear(year).stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .toList();
    }


}
