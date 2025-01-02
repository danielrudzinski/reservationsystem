package pl.myproject.reservation_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping("reservations")
    public ResponseEntity<Reservations> addReservation(@RequestBody Reservations reservation) {
        return reservationService.addReservation(reservation);
    }
    @DeleteMapping("reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {
        return reservationService.deleteReservationById(id);
    }
    @PutMapping("reservations/{id}")
    public ResponseEntity<Reservations> updateReservation(@PathVariable Integer id, @RequestBody Reservations reservation) {
        return reservationService.updateReservation(id, reservation);
    }
    @PatchMapping("reservations/{id}")
    public ResponseEntity<Reservations> patchReservation(@PathVariable Integer id, @RequestBody Reservations reservation) {
        return reservationService.patchReservation(id, reservation);
    }
}
