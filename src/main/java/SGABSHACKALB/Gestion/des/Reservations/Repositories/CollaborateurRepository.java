package SGABSHACKALB.Gestion.des.Reservations.Repositories;

import SGABSHACKALB.Gestion.des.Reservations.Entities.Collaborateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long> {
    public Collaborateur findByNom(String nom);
    public Collaborateur findByDomaine(String domaine);
}
