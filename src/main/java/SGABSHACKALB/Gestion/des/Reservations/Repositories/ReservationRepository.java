package SGABSHACKALB.Gestion.des.Reservations.Repositories;

import SGABSHACKALB.Gestion.des.Reservations.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    public Reservation findByPosition(String position);
    public void deleteById(Long id);
}
