package uni.java.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.java.springboot.exception.ResourceNotFoundException;
import uni.java.springboot.model.Car;
import uni.java.springboot.repository.CarRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/vehicle/")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    //get cars
    @GetMapping("/cars")
    public List<Car> getAllCar(){
        return carRepository.findAll();
    }

    //get cars by id
    @GetMapping("/cars/{id_exhibition}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id_exhibition") int idExhibition)
            throws ConfigDataResourceNotFoundException, ResourceNotFoundException {
        Car car = carRepository.findById(idExhibition)
                .orElseThrow( () -> new ResourceNotFoundException("Car not found for this id: " + idExhibition));
        return ResponseEntity.ok().body(car);
    }
    // get in
    @GetMapping("/cars/{id_exhibition}/hopon")
    public ResponseEntity<String> getInTheCar(@PathVariable(value = "id_exhibition") int idExhibition)
            throws ConfigDataResourceNotFoundException, ResourceNotFoundException {
        Car car = carRepository.findById(idExhibition)
                .orElseThrow( () -> new ResourceNotFoundException("Car not found for this id: " + idExhibition));
        return ResponseEntity.ok().body(car.sound());
    }

    //save car
    @PostMapping("/cars")
    public Car createCar(@RequestBody Car car){
        return carRepository.save(car);
    }

    //udate car
    @PutMapping("/cars/{id_exhibition}")
    public ResponseEntity<Car> updateEmployee(@PathVariable(value = "id_exhibition") int idExhibition,
                                                    @RequestBody Car carDetails) throws ResourceNotFoundException {
        Car car = carRepository.findById(idExhibition)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found for this id :: " + idExhibition));

        car.setId_exhibition(carDetails.getId_exhibition());
        car.setBrand(carDetails.getBrand());
        final Car updatedCar = carRepository.save(car);
        return ResponseEntity.ok(updatedCar);
    }

    //delete
    @DeleteMapping("/cars/{id_exhibition}")
    public Map<String, Boolean> deleteCar(@PathVariable(value = "id_exhibition") int idExhibition)
            throws ResourceNotFoundException {
        Car car = carRepository.findById(idExhibition)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found for this id :: " + idExhibition));

        carRepository.delete(car);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
