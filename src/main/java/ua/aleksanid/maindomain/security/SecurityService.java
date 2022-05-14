package ua.aleksanid.maindomain.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ua.aleksanid.maindomain.configurations.SecurityPropertiesConfiguration;

@Service
public class SecurityService {
    private final Logger logger = LogManager.getLogger(SecurityService.class.getName());

    private final SecurityPropertiesConfiguration securityProperties;

    public SecurityService(SecurityPropertiesConfiguration securityPropertiesConfiguration) {
        this.securityProperties = securityPropertiesConfiguration;
    }

    public boolean validateToken(String token) {
        if (token != null) {
            try {
                JWT.require(Algorithm.HMAC512(securityProperties.getSecret().getBytes()))
                        .build()
                        .verify(token.replace(securityProperties.getTokenPrefix(), ""));
                return true;
            } catch (TokenExpiredException exp) {
                logger.error("Token expired ", exp);
                return false;
            }
        }
        return false;
    }
}
