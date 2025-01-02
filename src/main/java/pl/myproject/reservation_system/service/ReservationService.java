package pl.myproject.reservation_system.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.myproject.reservation_system.model.Reservations;
import pl.myproject.reservation_system.repository.ReservationRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    public ResponseEntity<Iterable<Reservations>> getAllReservations() {
        Iterable<Reservations> reservations = reservationRepository.findAll();
        return ResponseEntity.ok(reservations);
    }
}
