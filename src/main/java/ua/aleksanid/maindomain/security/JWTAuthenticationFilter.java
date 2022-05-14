package ua.aleksanid.maindomain.security;


import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.aleksanid.maindomain.configurations.SecurityPropertiesConfiguration;
import ua.aleksanid.maindomain.models.AppUser;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final SecurityPropertiesConfiguration securityProperties;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, SecurityPropertiesConfiguration securityProperties) {
        this.authenticationManager = authenticationManager;
        this.securityProperties = securityProperties;
        this.setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) {
        try {
            AppUser credentials = new ObjectMapper()
                    .readValue(req.getInputStream(), AppUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getLogin(),
                            credentials.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            logger.error(e);
            throw new AuthException("Failed authentication", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        AuthUser user = ((AuthUser) auth.getPrincipal());

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + securityProperties.getExpirationTime()))
                .withIssuer(String.valueOf(user.getId()))
                .sign(HMAC512(securityProperties.getSecret().getBytes()));
        res.addHeader(securityProperties.getHeaderString(), securityProperties.getTokenPrefix() + token);
    }

    private class AuthException extends RuntimeException {
        public AuthException(String message) {
            super(message);
        }

        public AuthException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
