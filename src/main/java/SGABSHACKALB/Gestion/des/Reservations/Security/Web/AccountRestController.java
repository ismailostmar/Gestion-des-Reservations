package SGABSHACKALB.Gestion.des.Reservations.Security.Web;

import SGABSHACKALB.Gestion.des.Reservations.Security.Entities.AppRole;
import SGABSHACKALB.Gestion.des.Reservations.Security.Entities.AppUser;
import SGABSHACKALB.Gestion.des.Reservations.Security.Services.AccountService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AccountRestController {

    @Data
    class RoleUserForm{
        private String username;
        private String rolename;
    }
    private AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/users")
    @PostAuthorize("hasAuthority('USER')")
    public List<AppUser> appUsers(){
        return accountService.listUsers();
    }

    @PostMapping(path = "/users")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppUser saveUser(@RequestBody AppUser appUser){
        return accountService.addNewUser(appUser);
    }

    @PostMapping(path = "/roles")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppRole saveRole(@RequestBody AppRole appRole) {
        return accountService.addNewRole(appRole);
    }

    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
        accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRolename());
    }

    //  END-POINT OF REFRESH TOKEN

    @PostMapping("/refreshToken")
    public Map<String, String> refreshToken(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        if( token != null && token.startsWith("Bearer ") ){
            try {
                String jwtRefreshToken = token.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("HACKLABHACK");
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwtRefreshToken);
                String username = decodedJWT.getSubject();
                AppUser appUser = accountService.loadUserByUsername(username);
                String jwtAccessToken = JWT
                        .create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 2 * 6 *1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", appUser.getAppRoles().stream().map(e -> e.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> idToken = new HashMap<>();
                idToken.put("Access_Token", jwtAccessToken);
                idToken.put("Refresh_Token", jwtRefreshToken);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);
                return idToken;
            } catch (Exception e){
                throw e;
            }
        } else {
            throw new RuntimeException("Refresh Token required");
        }
    }
}
