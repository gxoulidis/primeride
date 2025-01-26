package gr.uom.primeride.repository;

import gr.uom.primeride.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository extends JpaRepository<Citizen, String> {
    boolean existsByEmail(String email);

}
