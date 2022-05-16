package uni.java.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.java.springboot.exception.ResourceNotFoundException;
import uni.java.springboot.model.Audit;
import uni.java.springboot.model.Bus;
import uni.java.springboot.model.Vehicle;
import uni.java.springboot.repository.AuditRepository;
import uni.java.springboot.repository.BusRepository;
import uni.java.springboot.service.SingletonWriteCSV;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/vehicle/")
public class BusController {

    @Autowired
    private BusRepository BusRepository;
    @Autowired
    private AuditRepository auditRepository;

    //get Buss
    @GetMapping("/bus")
    public List<Bus> getAllBus(){
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        for(Bus ob:BusRepository.findAll()){
            Vehicle v = (Vehicle) ob;
            vehicles.add(v);
        }
        SingletonWriteCSV.getInstance(vehicles);
        this.auditRepository.save(new Audit("getAllBus", new Timestamp(System.currentTimeMillis()) ));
        return BusRepository.findAll();
    }

    //get Buss by id
    @GetMapping("/bus/{id_exhibition}")
    public ResponseEntity<Bus> getBusById(@PathVariable(value = "id_exhibition") int idExhibition)
            throws ConfigDataResourceNotFoundException, ResourceNotFoundException {
        Bus bus = BusRepository.findById(idExhibition)
                .orElseThrow( () -> new ResourceNotFoundException("Bus not found for this id: " + idExhibition));
        this.auditRepository.save(new Audit("getBusById", new Timestamp(System.currentTimeMillis()) ));
        return ResponseEntity.ok().body(bus);
    }
    // get action
    @GetMapping("/bus/{id_exhibition}/action")
    public ResponseEntity<String> actionBus(@PathVariable(value = "id_exhibition") int idExhibition)
            throws ConfigDataResourceNotFoundException, ResourceNotFoundException {
        Bus bus = BusRepository.findById(idExhibition)
                .orElseThrow( () -> new ResourceNotFoundException("Bus not found for this id: " + idExhibition));
        this.auditRepository.save(new Audit("getBusAction", new Timestamp(System.currentTimeMillis()) ));
        this.auditRepository.save(new Audit("getBusAction", new Timestamp(System.currentTimeMillis()) ));
        return ResponseEntity.ok().body(bus.action());
    }

/*    //get all audis
    @GetMapping("/Buss/getAudi")
    public List<Bus> getAllAudis(){
        try {
            return this.BusRepository.findAllAudis();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            this.auditRepository.save(new Audit("getAllAudis", new Timestamp(System.currentTimeMillis()) ));
        }
    } */

    //get in
    @GetMapping("/bus/{id_exhibition}/getIn")
    public ResponseEntity<String> getInTheBus(@PathVariable(value = "id_exhibition") int idExhibition)
            throws ConfigDataResourceNotFoundException, ResourceNotFoundException {
        Bus bus = BusRepository.findById(idExhibition)
                .orElseThrow( () -> new ResourceNotFoundException("Buss not found for this id: " + idExhibition));
        this.auditRepository.save(new Audit("getInBus", new Timestamp(System.currentTimeMillis()) ));
        return ResponseEntity.ok().body(bus.openVehicle());
    }

    //save Bus
    @PostMapping("/bus")
    public Bus createBus(@RequestBody Bus bus){
        this.auditRepository.save(new Audit("postBus", new Timestamp(System.currentTimeMillis()) ));
        return BusRepository.save(bus);
    }

    //udate Bus
    @PutMapping("/bus/{id_exhibition}")
    public ResponseEntity<Bus> updateEmployee(@PathVariable(value = "id_exhibition") int idExhibition,
                                              @RequestBody Bus busDetails) throws ResourceNotFoundException {
        Bus bus = BusRepository.findById(idExhibition)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found for this id :: " + idExhibition));

        bus.setId_exhibition(busDetails.getId_exhibition());
        bus.setSeatNumber(busDetails.getSeatNumber());
        final Bus updatedBus = BusRepository.save(bus);
        this.auditRepository.save(new Audit("updateBus", new Timestamp(System.currentTimeMillis()) ));
        return ResponseEntity.ok(updatedBus);
    }

    //delete
    @DeleteMapping("/bus/{id_exhibition}")
    public Map<String, Boolean> deleteBus(@PathVariable(value = "id_exhibition") int idExhibition)
            throws ResourceNotFoundException {
        Bus bus = BusRepository.findById(idExhibition)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found for this id :: " + idExhibition));

        BusRepository.delete(bus);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        this.auditRepository.save(new Audit("deleteBus", new Timestamp(System.currentTimeMillis()) ));
        return response;
    }
}
