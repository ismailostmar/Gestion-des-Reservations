package SGABSHACKALB.Gestion.des.Reservations.Security.Services;

import SGABSHACKALB.Gestion.des.Reservations.Security.Entities.AppRole;
import SGABSHACKALB.Gestion.des.Reservations.Security.Entities.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole (AppRole appRole);
    void addRoleToUser ( String username, String roleName);
    AppUser loadUserByUsername( String username);
    List<AppUser> listUsers();
    List<AppRole> listRoles();
}
