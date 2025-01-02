package pl.myproject.reservation_system.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import pl.myproject.reservation_system.model.Reservations;
import pl.myproject.reservation_system.repository.ReservationRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /// get
    public ResponseEntity<Iterable<Reservations>> getAllReservations() {
        Iterable<Reservations> reservations = reservationRepository.findAll();
        return ResponseEntity.ok(reservations);
    }
    ///  get one findbyid
    public ResponseEntity<Reservations> getReservationById(@PathVariable Integer id) {
        return reservationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Reservations> addReservation(@RequestBody  Reservations reservation) {
        if(reservation.getNumberOfPeople()<1){
            return ResponseEntity.badRequest().body(null);
        }
        if(reservation.getCustomerName()==null){
            return ResponseEntity.badRequest().body(null);
        }
       if(reservation.getDate() == null){
           return ResponseEntity.badRequest().body(null);
       }


        Reservations savedReservation = reservationRepository.save(reservation);

        UriComponents uriComponents = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedReservation.getId());
        return ResponseEntity.created(uriComponents.toUri()).body(savedReservation);
    }

    public ResponseEntity<Void> deleteReservationById(@PathVariable Integer id) {
       if(!reservationRepository.existsById(id)){
           return ResponseEntity.notFound().build();
       }
       reservationRepository.deleteById(id);
       return ResponseEntity.noContent().build();
    }
    public ResponseEntity<Reservations>updateReservation(@PathVariable Integer id, @RequestBody Reservations reservation) {
       return reservationRepository.findById(id)
                .map(existingReservation ->{
                    existingReservation.setNumberOfPeople(reservation.getNumberOfPeople());
                    existingReservation.setDate(reservation.getDate());
                    existingReservation.setCustomerName(reservation.getCustomerName());

                    return reservationRepository.save(existingReservation);
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    public ResponseEntity<Reservations>patchReservation(@PathVariable Integer id, @RequestBody Reservations reservation) {
        return reservationRepository.findById(id)
                .map(existingReservation ->{
                     existingReservation.setNumberOfPeople(reservation.getNumberOfPeople());
                    if(reservation.getDate()!=null) existingReservation.setDate(reservation.getDate());
                    if(reservation.getCustomerName()!=null) existingReservation.setCustomerName(reservation.getCustomerName());

                    return reservationRepository.save(existingReservation);
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
