package SGABSHACKALB.Gestion.des.Reservations.Security.Filters;

import SGABSHACKALB.Gestion.des.Reservations.Security.JWTUtile;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/refreshToken")) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationToken = request.getHeader(JWTUtile.AUTH_HEADER);
            if (authorizationToken != null && authorizationToken.startsWith(JWTUtile.PREFIX)) {
                try {
                    // Test of Request & Verification
                    String jwt = authorizationToken.substring(7);
                    Algorithm algorithm = Algorithm.HMAC256(JWTUtile.SECRET);
                    // Verification
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                    String username = decodedJWT.getSubject();
                    Collection<GrantedAuthority> authorities = new ArrayList<>();
                    // The User here can be Authenticated
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    // Before Authentication
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    response.setHeader("Error-Message", e.getMessage());
                    // if the token has been expired the user will not have the key to access
                    response.sendError(HttpServletResponse.SC_FORBIDDEN); // 403
                }
            }
            else {
                filterChain.doFilter(request, response);
            }
        }
    }
}