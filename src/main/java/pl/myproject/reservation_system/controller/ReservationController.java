package pl.myproject.reservation_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.myproject.reservation_system.model.Reservations;
import pl.myproject.reservation_system.service.ReservationService;

@RestController
public class ReservationController {
    private final ReservationService reservationService;
    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping("reservations")
    public ResponseEntity<Iterable<Reservations>> getAllReservations() {
        return reservationService.getAllReservations();
    }
    @GetMapping("reservations/{id}")
    public ResponseEntity<Reservations> getReservationById(@PathVariable Integer id) {
        return reservationService.getReservationById(id);
    }

}
