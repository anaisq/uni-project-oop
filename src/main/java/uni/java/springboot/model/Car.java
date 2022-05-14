package uni.java.springboot.model;


import javax.persistence.*;

@Entity
@Table(name = "Cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id_exhibition;

    @Column(name = "model")
    protected String model;


    public Car() {
        super();
    }

    public Car(String model) {
        super();
        this.model = model;
    }

    public int getId_exhibition() {
        return id_exhibition;
    }

    public void setId_exhibition(int id_exhibition) {
        this.id_exhibition = id_exhibition;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}
