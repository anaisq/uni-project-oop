package uni.java.springboot.service;

import uni.java.springboot.model.Boat;
import uni.java.springboot.model.Bus;
import uni.java.springboot.model.Car;
import uni.java.springboot.model.Vehicle;

import java.io.FileWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class SingletonWriteCSV {
    private SingletonWriteCSV(List<Vehicle> vehicles) {
        StringBuilder stringBuilder = new StringBuilder();
        int nr =1;
        for(Vehicle v: vehicles){
            stringBuilder.append("Vehicle " + nr + "\n"  );
            stringBuilder.append(v.getId_exhibition() + ", ");
            stringBuilder.append(v.getBrand() + ", ");
            stringBuilder.append(v.getModel() + ", ");

            if(v instanceof Car){
                stringBuilder.append( ((Car) v).getEngine() + ";\n");
            }
            else if (v instanceof  Boat){
                stringBuilder.append( ((Boat) v).getBoatMotion()  + ";\n");
            }
            else{
                stringBuilder.append( ((Bus) v).getSeatNumber() + ";\n");
            }
            nr++;

        }

         String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        try(FileWriter writer = new FileWriter("src\\main\\resources\\static\\vehicle_"+timestamp+".csv")) {
            writer.write(stringBuilder.toString());
            System.out.println("File created");
        }catch (Exception e){
            System.out.println("Something went wrong " + e);
        }


    }
        public static SingletonWriteCSV getInstance(List<Vehicle> vehicle){
        return new SingletonWriteCSV(vehicle);
    }
}
