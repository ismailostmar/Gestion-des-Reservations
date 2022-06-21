package SGABSHACKALB.Gestion.des.Reservations.Security.Filters;


import SGABSHACKALB.Gestion.des.Reservations.Security.JWTUtile;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }

    /* This method consists in making an Authentication attempt with the username and the password */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request , HttpServletResponse response) throws AuthenticationException {
        System.out.println("Tentative d'Authentification");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        UsernamePasswordAuthenticationToken authenticationToken =  new
                UsernamePasswordAuthenticationToken(username,password);
        return authenticationManager.authenticate(authenticationToken);
    }

    /* JSON WEB TOKEN => HEADER PAYLOAD SIGNATURE */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("SuccessFul Authentication");
        User user = (User) authResult.getPrincipal();
        Algorithm algorithm1 = Algorithm.HMAC256(JWTUtile.SECRET);

        // JWT : Access Token

        String jwtAccessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JWTUtile.EXPIRE_ACCESS_TOKEN))
                .withIssuer(request.getRequestURL().toString())
                .withClaim(JWTUtile.CLAIM_NAME,user.getAuthorities().stream().map(ac -> ac.getAuthority()).collect(Collectors.toList()))
                .sign(algorithm1);

        // JWT : Refresh Token

        String jwtRefreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JWTUtile.EXPIRE_REFRESH_TOKEN))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm1);

        Map<String,String> IdTok = new HashMap<>();
        IdTok.put(JWTUtile.ACCESS_TKN,jwtAccessToken);
        IdTok.put(JWTUtile.REFRESH_TKN,jwtRefreshToken);
        response.setContentType(JWTUtile.CONTENT_TYPE);
        new ObjectMapper().writeValue(response.getOutputStream(),IdTok);
    }
}