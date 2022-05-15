package uni.java.springboot.model;


import uni.java.springboot.service.Boat_motion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name = "Boats")
public class Boat extends Vehicle{

    @Column(name = "boatMotion")
    private Boat_motion boatMotion;

    public Boat() { }

    public Boat(String brand, String model,Boat_motion boatMotion) {
        super(brand, model);
        this.boatMotion = boatMotion;
    }

    public Boat_motion getBoatMotion() {
        return boatMotion;
    }

    public void setBoatMotion(Boat_motion boatMotion) {
        this.boatMotion = boatMotion;
    }

    @Override
    public String action() {
        return "Boat walks on water";
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Boat{" +
                "boatMotion=" + boatMotion +
                '}';
    }
}
