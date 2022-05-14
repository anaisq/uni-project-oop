package uni.java.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.java.springboot.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
}
