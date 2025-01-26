package gr.uom.primeride.controller;

import gr.uom.primeride.model.Booking;
import gr.uom.primeride.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/{afm}")
    public ResponseEntity<List<Booking>> getBookingsByAfm(@PathVariable String afm,
                                                          @RequestHeader("user_type") String user_type)
    {
        return bookingService.getAllBookingsByAfm(afm, user_type);
    }

    @GetMapping("/car/{car_id}")
    public ResponseEntity<Integer> getCarBookingsCount(@PathVariable Long car_id,
                                                       @RequestHeader("afm") String afm)
    {
        return bookingService.getBookingsCountByCarId(car_id, afm);
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody Booking booking,
                                                 @RequestHeader("afm") String afm,
                                                 @RequestHeader("car_id") Long car_id)
    {
        return bookingService.createBooking(booking, afm, car_id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id,
                                              @RequestHeader("afm") String afm,
                                              @RequestHeader("user_type") String user_type ,
                                              @RequestHeader("purchase") String purchase)
    {
        return bookingService.deleteBookingById(id, afm, user_type, purchase);
    }
}
