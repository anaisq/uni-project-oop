package uni.java.springboot.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Cars")
public class Car extends Vehicle{

    @Column(name = "engine")
    protected String engine;

    public Car() { }

    public Car(String brand, String model,String engine) {
        super(brand, model);
        this.engine = engine;
    }

    public String action(){return "The car does honk-honk."; }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +"Car{" +
                "engine='" + engine + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(engine, car.engine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engine);
    }
}
