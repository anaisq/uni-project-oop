package uni.java.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.java.springboot.model.Boat;

public interface BoatRepository extends JpaRepository<Boat, Integer> {

}
