package uni.java.springboot.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Bus")
public class Bus extends Vehicle{

    private int seatNumber;

    public Bus() {}

    public Bus(String brand, String model, int seatNumber) {
        super(brand, model);
        this.seatNumber = seatNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String action() {
        return "Bus walks children to school.";
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Bus{" +
                "seatNumber=" + seatNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return seatNumber == bus.seatNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatNumber);
    }
}
