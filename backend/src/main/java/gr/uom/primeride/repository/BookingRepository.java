package gr.uom.primeride.repository;

import gr.uom.primeride.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b WHERE b.citizen.afm = :afm")
    List<Booking> findAllByCitizenAfm(String afm);

    @Query("SELECT b FROM Booking b WHERE b.car.dealership.afm = :afm")
    List<Booking> findAllByDealershipfm(String afm);

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.car.id = :carId")
    int countBookingsByCarId(Long carId);
}
