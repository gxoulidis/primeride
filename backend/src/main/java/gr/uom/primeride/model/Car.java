package gr.uom.primeride.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Car {
    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Brand cannot be null")
    @NotEmpty(message = "Brand cannot be empty")
    @JsonProperty("brand")
    private String brand;

    @NotNull(message = "Model cannot be null")
    @NotEmpty(message = "Model cannot be empty")
    @JsonProperty("model")
    private String model;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Fuel type cannot be empty")
    @JsonProperty("fuel")
    @Column(name = "fuel_type")
    private FuelType fuel;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Engine type cannot be empty")
    @JsonProperty("engine")
    @Column(name = "engine_type")
    private EngineType engine;

    @NotNull(message = "Seats cannot be empty")
    @Positive(message = "Seats must be greater than 0")
    @JsonProperty("seats")
    private Integer seats;

    @NotNull(message = "Price cannot be empty")
    @Positive(message = "Price must be greater than 0")
    @JsonProperty("price")
    private Double price;

    @JsonProperty("info")
    @Column(name = "additional_info")
    private String info;

    @NotNull(message = "Number of cars cannot be empty")
    @JsonProperty("count")
    @Column(name = "number_of_cars")
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "dealership_afm")
    private Dealership dealership;

    public Car(){}
    public Car(String brand, String model, FuelType fuel, EngineType engine, Integer seats, Double price, String info, Integer count) {
        this.brand = brand;
        this.model = model;
        this.fuel = fuel;
        this.engine = engine;
        this.seats = seats;
        this.price = price;
        this.info = info;
        this.count = count;
    }
    public Car(String brand, String model, FuelType fuel, EngineType engine, Integer seats, Double price, String info, Integer count, Dealership dealership) {
        this.brand = brand;
        this.model = model;
        this.fuel = fuel;
        this.engine = engine;
        this.seats = seats;
        this.price = price;
        this.info = info;
        this.count = count;
        this.dealership = dealership;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public FuelType getFuel() {
        return this.fuel;
    }

    public void setFuel(FuelType fuel) {
        this.fuel = fuel;
    }

    public EngineType getEngine() {
        return this.engine;
    }

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    public Integer getSeats() {
        return this.seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAdditionalInfo() {
        return this.info;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.info = additionalInfo;
    }

    public Integer getNumberOfCars() {
        return this.count;
    }

    public void setNumberOfCars(Integer numberOfCars) {
        this.count = numberOfCars;
    }

    public Dealership getDealership() {
        return this.dealership;
    }

    public void setDealership(Dealership dealership) {
        this.dealership = dealership;
    }
}
