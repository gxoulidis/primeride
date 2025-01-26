package gr.uom.primeride.service;

import gr.uom.primeride.exception.FormException;
import gr.uom.primeride.exception.ResourceNotFoundException;
import gr.uom.primeride.model.Booking;
import gr.uom.primeride.model.Citizen;
import gr.uom.primeride.repository.BookingRepository;
import gr.uom.primeride.repository.CarRepository;
import gr.uom.primeride.repository.CitizenRepository;
import gr.uom.primeride.repository.DealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CitizenRepository citizenRepository;
    private final CarRepository carRepository;
    private final DealershipRepository dealershipRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          CitizenRepository citizenRepository,
                          CarRepository carRepository, DealershipRepository dealershipRepository)
    {
        this.bookingRepository = bookingRepository;
        this.citizenRepository = citizenRepository;
        this.carRepository = carRepository;
        this.dealershipRepository = dealershipRepository;
    }

    public ResponseEntity<List<Booking>> getAllBookingsByAfm(String afm, String user_type)
    {
        if (userExists(afm, user_type))
        {
            List<Booking> bookings = user_type.equals("citizen") ? bookingRepository.findAllByCitizenAfm(afm) : bookingRepository.findAllByDealershipfm(afm);
            if (bookings.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(bookings);
            }
            return ResponseEntity.status(HttpStatus.OK).body(bookings);
        }
        // this will never happen
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    public ResponseEntity<Integer> getBookingsCountByCarId(Long car_id, String afm)
    {
        var carOptional = carRepository.findById(car_id);

        if (carOptional.isPresent())
        {
            var car = carOptional.get();
            if (!car.getDealership().getAfm().equals(afm))
            {
                throw new ResourceNotFoundException.Builder("Invalid Dealership Afm")
                        .addDetail("afm", "You dont have the authority to view other dealerships's bookings")
                        .build();
            }

            int count = bookingRepository.countBookingsByCarId(car_id);

            if (count == 0)
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(0);
            else
                return ResponseEntity.status(HttpStatus.OK).body(count);

        }
        else
        {
            throw new ResourceNotFoundException.Builder("Car Not Found")
                    .addDetail("id", "Car with the given ID does not exist")
                    .build();
        }
    }

    public ResponseEntity<Booking> createBooking(Booking booking, String afm, Long car_id)
    {
        validateBooking(booking);

        var citizen = citizenRepository.findById(afm);

        if (citizen.isPresent())
        {
            booking.setCitizen(citizen.get());
            var carOptional = carRepository.findById(car_id);

            if (carOptional.isPresent())
            {
                var car = carOptional.get();
                if (car.getNumberOfCars() <= 0)
                {
                    throw new FormException.Builder("Booking Failed")
                            .addDetail("availability", "The selected car is no longer available for booking")
                            .build();
                }

                booking.setCar(car);
                car.setNumberOfCars(car.getNumberOfCars() - 1);
                carRepository.save(car);

                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(bookingRepository.save(booking));
            } else {
                throw new ResourceNotFoundException.Builder("Car not found")
                        .addDetail("car_id", "No car with this car_id exists")
                        .build();
            }
        } else {
            throw new ResourceNotFoundException.Builder("Citizen not found")
                    .addDetail("afm", "No dealership with this AFM exists")
                    .build();
        }

    }

    public ResponseEntity<Void> deleteBookingById(Long id, String afm, String user_type ,String purchase)
    {
        boolean isPurchase = convertToBoolean(purchase);
        var bookingOptional  = bookingRepository.findById(id);

        if (bookingOptional.isEmpty())
        {
            throw new ResourceNotFoundException.Builder("Booking Not Found")
                    .addDetail("id", "Booking with given ID does not exist")
                    .build();
        }

        var booking = bookingOptional.get();
        if (user_type.equals("citizen"))
        {
            if (!booking.getCitizen().getAfm().equals(afm)) {
                throw new ResourceNotFoundException.Builder("Booking Cancellation Failed")
                        .addDetail("afm", "You are not authorized to cancel this booking")
                        .build();
            }
        }
        else if (user_type.equals("dealership"))
        {
            if (!booking.getCar().getDealership().getAfm().equals(afm))
            {
                throw new ResourceNotFoundException.Builder("Booking Cancellation Failed")
                        .addDetail("afm", "You are not authorized to cancel this booking")
                        .build();
            }
        }
        else
        {
            throw new ResourceNotFoundException.Builder("Booking Cancellation Failed")
                    .addDetail("user_type", "Invalid user_type header")
                    .build();
        }

        var carOptional = carRepository.findById(booking.getCar().getId());
        if (carOptional.isEmpty())
        {
            throw new ResourceNotFoundException.Builder("Car Not Found")
                    .addDetail("id", "Car with the given ID does not exist")
                    .build();
        }

        var car = carOptional.get();
        if (!isPurchase)
        {
            car.setNumberOfCars(car.getNumberOfCars() + 1);
            carRepository.save(car);
        }

        bookingRepository.delete(booking);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private boolean userExists(String afm, String user_type) {
        var user_exists = false;
        if (user_type.equals("citizen")){
            var citizen = citizenRepository.findById(afm);
            user_exists = citizen.isPresent();
            if (!user_exists)
            {
                throw new ResourceNotFoundException.Builder("Citizen Not Found")
                        .addDetail("afm", "No citizen with this AFM exists")
                        .build();
            }
        }else if (user_type.equals("dealership")){
            var dealership = dealershipRepository.findById(afm);
            user_exists = dealership.isPresent();
            if (!user_exists)
            {
                throw new ResourceNotFoundException.Builder("Dealership Not Found")
                        .addDetail("afm", "No dealership with this AFM exists")
                        .build();
            }
        }else{
            throw new ResourceNotFoundException.Builder("User Not Found")
                    .addDetail("user_type", "User Type is not Valid")
                    .build();
        }
        return user_exists;
    }

    private void validateBooking(Booking booking) {
        LocalDate today = LocalDate.now();
        if (booking.getDate().isBefore(today))
        {
            throw new FormException.Builder("Booking Validation Failed")
                    .addDetail("date", "Booking date cannot be before today's date")
                    .build();
        }

        if (booking.getDaysOfBooking() > 30)
        {
            throw new FormException.Builder("Booking Validation Failed")
                    .addDetail("days", "The number of booking days cannot exceed 30")
                    .build();
        }
    }

    private boolean convertToBoolean(String string) {
        if (string.equals("true"))
            return true;
        else if (string.equals("false"))
            return false;
        else
            throw new ResourceNotFoundException.Builder("Purchase Header Not Valid")
                    .addDetail("purchase", "True or False")
                    .build();
    }
}
