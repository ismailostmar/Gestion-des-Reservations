package SGABSHACKALB.Gestion.des.Reservations.Security.Services;

import SGABSHACKALB.Gestion.des.Reservations.Security.Entities.AppUser;
import SGABSHACKALB.Gestion.des.Reservations.Security.Repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private   AppUserRepository appUserRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(AppUser appUser) {
        String pw = appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(pw));
        return appUserRepository.save(appUser);
    }
    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
    @Override
    public List<AppUser> listUsers() {
        return appUserRepository.findAll();
    }
}