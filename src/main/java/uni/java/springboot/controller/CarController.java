package uni.java.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.java.springboot.exception.ResourceNotFoundException;
import uni.java.springboot.model.Audit;
import uni.java.springboot.model.Car;
import uni.java.springboot.model.Vehicle;
import uni.java.springboot.repository.AuditRepository;
import uni.java.springboot.repository.CarRepository;
import uni.java.springboot.service.SingletonWriteCSV;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/vehicle/")
public class CarController {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private AuditRepository auditRepository;

    //get cars
    @GetMapping("/cars")
    public List<Car> getAllCar(){
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        for(Car ob:carRepository.findAll()){
            Vehicle v = (Vehicle) ob;
            vehicles.add(v);
        }
        SingletonWriteCSV.getInstance(vehicles);
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
    @GetMapping("/cars/{id_exhibition}/action")
    public ResponseEntity<String> getInTheCar(@PathVariable(value = "id_exhibition") int idExhibition)
            throws ConfigDataResourceNotFoundException, ResourceNotFoundException {
        Car car = carRepository.findById(idExhibition)
                .orElseThrow( () -> new ResourceNotFoundException("Car not found for this id: " + idExhibition));
        return ResponseEntity.ok().body(car.action());
    }

    //get all audis
    @GetMapping("/cars/getAudi")
    public List<Car> getAllAudis(){
        try {
            return this.carRepository.findAllAudis();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            this.auditRepository.save(new Audit("getAllAudis", new Timestamp(System.currentTimeMillis()) ));
        }
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
        car.setEngine(carDetails.getEngine());
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
