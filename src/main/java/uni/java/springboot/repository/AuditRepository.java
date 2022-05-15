package uni.java.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.java.springboot.model.Audit;

public interface AuditRepository extends JpaRepository<Audit, Integer> {
}
