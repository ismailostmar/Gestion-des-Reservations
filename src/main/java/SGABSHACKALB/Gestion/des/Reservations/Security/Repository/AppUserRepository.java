package SGABSHACKALB.Gestion.des.Reservations.Security.Repository;

import SGABSHACKALB.Gestion.des.Reservations.Security.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}