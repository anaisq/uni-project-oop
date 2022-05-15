package uni.java.springboot.model;

import java.util.Random;

abstract class Vehicle {
    public abstract String sound();
    public String openVehicle() {
        Random x = new Random();
        if (x.nextBoolean() == true ) {
            return "Someone's in the car.";
        } else {
            return "You can go inside, the car is empty";
        }
    }
}
