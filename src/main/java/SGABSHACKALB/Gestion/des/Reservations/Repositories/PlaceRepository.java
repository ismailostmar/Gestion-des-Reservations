package SGABSHACKALB.Gestion.des.Reservations.Repositories;

import SGABSHACKALB.Gestion.des.Reservations.Entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PlaceRepository extends JpaRepository<Place,Long> {

}
