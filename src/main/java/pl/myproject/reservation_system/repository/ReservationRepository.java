package pl.myproject.reservation_system.repository;

import org.springframework.data.repository.CrudRepository;
import pl.myproject.reservation_system.model.Revervation;

public interface ReservationRepository extends CrudRepository<Revervation,Integer> {
}
