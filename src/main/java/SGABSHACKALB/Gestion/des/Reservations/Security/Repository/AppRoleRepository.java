package SGABSHACKALB.Gestion.des.Reservations.Security.Repository;

import SGABSHACKALB.Gestion.des.Reservations.Security.Entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName( String roleName);
}