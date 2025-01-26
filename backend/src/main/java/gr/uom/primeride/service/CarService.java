package gr.uom.primeride.service;

import gr.uom.primeride.exception.FormException;
import gr.uom.primeride.exception.ResourceNotFoundException;
import gr.uom.primeride.model.Car;
import gr.uom.primeride.model.EngineType;
import gr.uom.primeride.model.FuelType;
import gr.uom.primeride.repository.CarRepository;
import gr.uom.primeride.repository.DealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final DealershipRepository dealershipRepository;

    @Autowired
    public CarService(CarRepository carRepository, DealershipRepository dealershipRepository) {
        this.carRepository = carRepository;
        this.dealershipRepository = dealershipRepository;
    }

    public ResponseEntity<List<Car>> getAllCars()
    {
            List<Car> cars = carRepository.findAll().stream().filter(x->(x.getNumberOfCars() > 0)).collect(Collectors.toList());

            if (cars.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cars);

            return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    public ResponseEntity<List<Car>> getCars(String brand, String model, FuelType fuel, EngineType engine,
                                             Integer seats, Double min_price, Double max_price, String dealership_afm)
    {
        List<Car> cars = carRepository.findAll()
                .stream()
                .filter(car -> (car.getNumberOfCars() > 0))
                .filter(car -> (brand == null || car.getBrand().equalsIgnoreCase(brand)))
                .filter(car -> (model == null || car.getModel().equalsIgnoreCase(model)))
                .filter(car -> (fuel == null || car.getFuel().equals(fuel)))
                .filter(car -> (engine == null || car.getEngine().equals(engine)))
                .filter(car -> (seats == null || car.getSeats().equals(seats)))
                .filter(car -> (min_price == null || car.getPrice() >= min_price))
                .filter(car -> (max_price == null || car.getPrice() <= max_price))
                .filter(car -> (dealership_afm == null || car.getDealership().getAfm().equals(dealership_afm)))
                .collect(Collectors.toList());

        if (cars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cars);
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    public ResponseEntity<List<Car>> getCarsByAfm(String afm)
    {
        var dealership = dealershipRepository.findById(afm);
        if (dealership.isPresent())
        {
            List<Car> cars = carRepository.findAllByDealershipAfm(afm);
            if (cars.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cars);

            return ResponseEntity.status(HttpStatus.OK).body(cars);
        }
        else
        {
            throw new ResourceNotFoundException.Builder("User Not Found")
                    .addDetail("afm", "No dealership with this AFM exists")
                    .build();
        }
    }

    public ResponseEntity<Car> addNewCar(String afm, Car car) {
        validateCarFieldCorrectness(car);

        var dealership = dealershipRepository.findById(afm);
        if (dealership.isPresent()) {
            car.setDealership(dealership.get());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(carRepository.save(car));
        }
        else
        {
            throw new ResourceNotFoundException.Builder("User not found")
                    .addDetail("afm", "No dealership with this AFM exists")
                    .build();
        }
    }

    public ResponseEntity<Car> updateCarAvailability(Long id, String afm, Map<String, Integer> updates) {
        var carOptional = carRepository.findById(id);

        if (carOptional.isEmpty())
        {
            throw new ResourceNotFoundException.Builder("Car not found")
                    .addDetail("id", "Car with the given ID does not exist")
                    .build();
        }

        var car = carOptional.get();
        if(!car.getDealership().getAfm().equals(afm))
        {
            throw new FormException.Builder("Car Update Failed")
                    .addDetail("afm", "The car belongs to another dealership")
                    .build();
        }


        if (updates.containsKey("count")) {
            Integer countValue = updates.get("count");

            if (!countValue.equals(car.getNumberOfCars())) {
                if (countValue > 0)
                    car.setNumberOfCars(countValue);
                else
                    throw new FormException.Builder("Car Update Failed")
                            .addDetail("count", "Number of Cars must be greater than 0")
                            .build();
            }
        }
        else
        {
            throw new FormException.Builder("Car Update Failed")
                    .addDetail("count", "Should be contained in request")
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carRepository.save(car));
    }

    private void validateCarFieldCorrectness(Car car)
    {
        var exceptionBuilder = new FormException.Builder("Car Creation Failed");
        String brand = car.getBrand().trim();
        String model = car.getModel().trim();
        Integer count = car.getNumberOfCars();

        if(brand.length() < 3)
            exceptionBuilder.addDetail("brand","Brand must be at least 3 characters");

        if(model.length() < 2)
            exceptionBuilder.addDetail("model","Model must be at least 2 characters");

        if(count < 0)
            exceptionBuilder.addDetail("count","Number of Cars must be greater than 0");

        if (!exceptionBuilder.getDetails().isEmpty())
            throw exceptionBuilder.build();
    }

}
