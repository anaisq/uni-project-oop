package uni.java.springboot.model;

import javax.persistence.*;
import java.util.Random;

@MappedSuperclass
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id_exhibition;

    @Column(name = "brand")
    protected String brand;

    @Column(name = "model")
    protected String model;

    public Vehicle(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }
    public Vehicle(){ }

    public String action() { return null;};

    public String openVehicle() {
        Random x = new Random();
        if (x.nextBoolean() == true ) {
            return "Someone's in the car.";
        } else {
            return "You can go inside, the vehicle is empty";
        }
    }

    public int getId_exhibition() {
        return id_exhibition;
    }

    public void setId_exhibition(int id_exhibition) {
        this.id_exhibition = id_exhibition;
    }

    public void setBrand(String brand) {this.brand = brand;}

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id_exhibition=" + id_exhibition +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    public void setModel(String model) {
        this.model = model;
    }
}
