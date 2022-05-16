package uni.java.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.java.springboot.exception.ResourceNotFoundException;
import uni.java.springboot.model.Audit;
import uni.java.springboot.model.Boat;
import uni.java.springboot.model.Vehicle;
import uni.java.springboot.repository.AuditRepository;
import uni.java.springboot.repository.BoatRepository;
import uni.java.springboot.service.SingletonWriteCSV;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/vehicle/")
public class BoatController {

    @Autowired
    private BoatRepository BoatRepository;
    @Autowired
    private AuditRepository auditRepository;

    //get Boats
    @GetMapping("/boat")
    public List<Boat> getAllBoat(){
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        for(Boat ob:BoatRepository.findAll()){
            Vehicle v = (Vehicle) ob;
            vehicles.add(v);
        }
        SingletonWriteCSV.getInstance(vehicles);
        this.auditRepository.save(new Audit("getAllBoat", new Timestamp(System.currentTimeMillis()) ));
        return BoatRepository.findAll();
    }

    //get Boats by id
    @GetMapping("/boat/{id_exhibition}")
    public ResponseEntity<Boat> getBoatById(@PathVariable(value = "id_exhibition") int idExhibition)
            throws ConfigDataResourceNotFoundException, ResourceNotFoundException {
        Boat boat = BoatRepository.findById(idExhibition)
                .orElseThrow( () -> new ResourceNotFoundException("Boat not found for this id: " + idExhibition));
        this.auditRepository.save(new Audit("getBoatById", new Timestamp(System.currentTimeMillis()) ));
        return ResponseEntity.ok().body(boat);
    }
    // get action
    @GetMapping("/boat/{id_exhibition}/action")
    public ResponseEntity<String> actionBoat(@PathVariable(value = "id_exhibition") int idExhibition)
            throws ConfigDataResourceNotFoundException, ResourceNotFoundException {
        Boat boat = BoatRepository.findById(idExhibition)
                .orElseThrow( () -> new ResourceNotFoundException("Boat not found for this id: " + idExhibition));
        this.auditRepository.save(new Audit("getBoatAction", new Timestamp(System.currentTimeMillis()) ));
        this.auditRepository.save(new Audit("getBoatAction", new Timestamp(System.currentTimeMillis()) ));
        return ResponseEntity.ok().body(boat.action());
    }

/*    //get all audis
    @GetMapping("/Boats/getAudi")
    public List<Boat> getAllAudis(){
        try {
            return this.BoatRepository.findAllAudis();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            this.auditRepository.save(new Audit("getAllAudis", new Timestamp(System.currentTimeMillis()) ));
        }
    } */

    //get in
    @GetMapping("/boat/{id_exhibition}/getIn")
    public ResponseEntity<String> getInTheBoat(@PathVariable(value = "id_exhibition") int idExhibition)
            throws ConfigDataResourceNotFoundException, ResourceNotFoundException {
        Boat boat = BoatRepository.findById(idExhibition)
                .orElseThrow( () -> new ResourceNotFoundException("Boats not found for this id: " + idExhibition));
        this.auditRepository.save(new Audit("getInBoat", new Timestamp(System.currentTimeMillis()) ));
        return ResponseEntity.ok().body(boat.openVehicle());
    }

    //save Boat
    @PostMapping("/boat")
    public Boat createBoat(@RequestBody Boat boat){
        this.auditRepository.save(new Audit("postBoat", new Timestamp(System.currentTimeMillis()) ));
        return BoatRepository.save(boat);
    }

    //udate Boat
    @PutMapping("/boat/{id_exhibition}")
    public ResponseEntity<Boat> updateEmployee(@PathVariable(value = "id_exhibition") int idExhibition,
                                              @RequestBody Boat boatDetails) throws ResourceNotFoundException {
        Boat boat = BoatRepository.findById(idExhibition)
                .orElseThrow(() -> new ResourceNotFoundException("Boat not found for this id :: " + idExhibition));

        boat.setId_exhibition(boatDetails.getId_exhibition());
        boat.setBoatMotion(boatDetails.getBoatMotion());
        final Boat updatedBoat = BoatRepository.save(boat);
        this.auditRepository.save(new Audit("updateBoat", new Timestamp(System.currentTimeMillis()) ));
        return ResponseEntity.ok(updatedBoat);
    }

    //delete
    @DeleteMapping("/boat/{id_exhibition}")
    public Map<String, Boolean> deleteBoat(@PathVariable(value = "id_exhibition") int idExhibition)
            throws ResourceNotFoundException {
        Boat boat = BoatRepository.findById(idExhibition)
                .orElseThrow(() -> new ResourceNotFoundException("Boat not found for this id :: " + idExhibition));

        BoatRepository.delete(boat);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        this.auditRepository.save(new Audit("deleteBoat", new Timestamp(System.currentTimeMillis()) ));
        return response;
    }
}
