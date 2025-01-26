package gr.uom.primeride.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Date cannot be null")
    @JsonProperty("date")
    @Column(name = "date")
    private LocalDate date;

    @NotNull(message = "Days of booking cannot be empty")
    @JsonProperty("days")
    @Positive(message = "Days of booking must be greater than 0")
    @Column(name = "days_of_booking")
    private Integer daysOfBooking;

    @ManyToOne
    @JoinColumn(name = "citizen_afm")
    private Citizen citizen;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Booking() {}
    public Booking(LocalDate date, Integer days ){
        this.date = date;
        this.daysOfBooking = days;
    }

    public Booking(LocalDate date, Integer days, Citizen citizen, Car car ) {
        this.date = date;
        this.daysOfBooking = days;
        this.citizen = citizen;
        this.car = car;
    }

    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return this.date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Integer getDaysOfBooking() { return this.daysOfBooking; }
    public void setDaysOfBooking(Integer days) { this.daysOfBooking = days; }

    public Citizen getCitizen() { return this.citizen; }
    public void setCitizen(Citizen citizen) { this.citizen = citizen; }

    public Car getCar() { return this.car; }
    public void setCar(Car car) { this.car = car; }
}
