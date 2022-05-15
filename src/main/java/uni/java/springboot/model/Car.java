package uni.java.springboot.model;


import javax.persistence.*;

@Entity
@Table(name = "Cars")
public class Car extends Vehicle{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id_exhibition;

    @Column(name = "brand")
    protected String brand;


    public Car() { }

    public Car(String brand) {
        this.brand = brand;
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

    public String sound(){return "The car does honk-honk."; }
}
