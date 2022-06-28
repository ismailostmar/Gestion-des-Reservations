package SGABSHACKALB.Gestion.des.Reservations.Security.Services;

import SGABSHACKALB.Gestion.des.Reservations.Security.Entities.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppUser loadUserByUsername( String username);
    List<AppUser> listUsers();
}
