package gr.uom.primeride.repository;

import gr.uom.primeride.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT c FROM Car c WHERE c.dealership.afm = :afm")
    List<Car> findAllByDealershipAfm(@Param("afm") String afm);
}
