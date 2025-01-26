package gr.uom.primeride.controller;

import gr.uom.primeride.model.Car;
import gr.uom.primeride.model.EngineType;
import gr.uom.primeride.model.FuelType;
import gr.uom.primeride.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Car>> getCars(@RequestParam(name = "brand",required = false) String brand,
                                             @RequestParam(name = "model",required = false) String model,
                                             @RequestParam(name = "fuel",required = false) FuelType fuel,
                                             @RequestParam(name = "engine",required = false) EngineType engine,
                                             @RequestParam(name = "seats",required = false) Integer seats,
                                             @RequestParam(name = "min-price",required = false) Double min_price,
                                             @RequestParam(name = "max-price",required = false) Double max_price,
                                             @RequestParam(name = "dealership",required = false) String dealership_afm)
    {
        return carService.getCars(brand,model,fuel,engine,seats,min_price,max_price,dealership_afm);
    }

    @GetMapping("/{afm}")
    public ResponseEntity<List<Car>> getCarsByAfm(@PathVariable String afm)
    {
        return carService.getCarsByAfm(afm);
    }

    @PostMapping("")
    public ResponseEntity<Car> addCar(@Valid @RequestBody Car car,
                                      @RequestHeader("afm") String afm)
    {
        return carService.addNewCar(afm, car);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Car> updateCarAvailability(@PathVariable Long id,
                                                     @RequestHeader("afm") String afm ,
                                                     @RequestBody Map<String, Integer> updates)
    {
        return carService.updateCarAvailability(id, afm, updates);
    }
}
