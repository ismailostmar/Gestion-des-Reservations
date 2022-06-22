package SGABSHACKALB.Gestion.des.Reservations;

import SGABSHACKALB.Gestion.des.Reservations.Security.Entities.AppRole;
import SGABSHACKALB.Gestion.des.Reservations.Security.Entities.AppUser;
import SGABSHACKALB.Gestion.des.Reservations.Security.Services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication
@EnableSwagger2
// PrePost Enabled & SecureEnabled
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class GestionDesReservationsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestionDesReservationsApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(AccountService accountService) {
        return args -> {
            accountService.addNewRole(new AppRole(null, "ADMIN"));
            accountService.addNewUser(new AppUser(null, "USER", "1234", new ArrayList<>()));
            accountService.addRoleToUser("USER", "ADMIN");
        };
    }
}