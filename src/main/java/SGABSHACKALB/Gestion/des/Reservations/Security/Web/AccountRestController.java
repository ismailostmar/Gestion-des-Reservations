package SGABSHACKALB.Gestion.des.Reservations.Security.Web;

import SGABSHACKALB.Gestion.des.Reservations.Security.Entities.AppUser;
import SGABSHACKALB.Gestion.des.Reservations.Security.JWTUtile;
import SGABSHACKALB.Gestion.des.Reservations.Security.Services.AccountService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* Controller Account */
@RestController
public class AccountRestController {
    private final AccountService accountService;

    /* Constrcutor with arguments */
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/users")
    public List<AppUser> appUsers() {
        return accountService.listUsers();
    }

    @PostMapping(path = "/users")
    public AppUser saveUser(@RequestBody AppUser appUser) {
        return accountService.addNewUser(appUser);
    }

    //  END-POINT OF REFRESH TOKEN for re-generate the Access Token
    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authToken = request.getHeader(JWTUtile.AUTH_HEADER);
        if (authToken != null && authToken.startsWith(JWTUtile.PREFIX)) {
            try {
                String jwtRefreshToken = authToken.substring(JWTUtile.PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(JWTUtile.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwtRefreshToken);
                String username = decodedJWT.getSubject();
                AppUser appUser = accountService.loadUserByUsername(username);
                String jwtAccessToken = JWT
                        .create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtile.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algorithm);

                Map<String, String> idToken = new HashMap<>();
                idToken.put(JWTUtile.ACCESS_TKN, jwtAccessToken);
                idToken.put(JWTUtile.REFRESH_TKN, jwtRefreshToken);
                response.setContentType(JWTUtile.CONTENT_TYPE);
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new RuntimeException(JWTUtile.REFRESH_TOKEN_REQUIRED);
        }
    }

    @GetMapping(path = "/profile")
    public AppUser profileUser(Principal principal) {
        return accountService.loadUserByUsername(principal.getName());
    }
}