package uni.java.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.java.springboot.model.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer> {

}