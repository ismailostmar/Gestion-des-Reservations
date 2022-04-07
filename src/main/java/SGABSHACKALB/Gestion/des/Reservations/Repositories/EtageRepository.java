package SGABSHACKALB.Gestion.des.Reservations.Repositories;

import SGABSHACKALB.Gestion.des.Reservations.Entities.Etage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EtageRepository extends JpaRepository<Etage,Long> {
    public Etage findByDomaineEtage(String domaineEtage);
}
