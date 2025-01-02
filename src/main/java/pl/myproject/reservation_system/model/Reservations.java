package pl.myproject.reservation_system.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity

public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String customerName;
    private LocalDateTime date;
    private int numberOfPeople;

    public Reservations() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime reservationDate) {
        this.date = reservationDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
