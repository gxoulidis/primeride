package gr.uom.primeride.repository;

import gr.uom.primeride.model.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealershipRepository extends JpaRepository<Dealership, String> {
    boolean existsByEmail(String email);

}
