package uni.java.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.java.springboot.model.Car;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
    @Query(value = "select * from cars where lower(brand) LIKE '%audi%'", nativeQuery = true)
    List<Car> findAllAudis();
}
