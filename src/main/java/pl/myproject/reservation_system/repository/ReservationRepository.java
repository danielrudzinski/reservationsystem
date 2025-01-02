package pl.myproject.reservation_system.repository;

import org.springframework.data.repository.CrudRepository;
import pl.myproject.reservation_system.model.Reservations;

public interface ReservationRepository extends CrudRepository<Reservations,Integer> {
}
