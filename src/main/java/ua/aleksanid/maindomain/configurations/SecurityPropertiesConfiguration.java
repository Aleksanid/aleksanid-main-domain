package ua.aleksanid.maindomain.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ua.aleksanid.maindomain.util.YamlPropertySourceFactory;

@Configuration
@PropertySource(value = "classpath:security.yml", factory = YamlPropertySourceFactory.class)
public class SecurityPropertiesConfiguration {
    @Value("${security.secret}")
    private String secret;
    @Value("${security.expirationTime}")
    private long expirationTime;
    @Value("${security.tokenPrefix}")
    private String tokenPrefix;
    @Value("${security.headerString}")
    private String headerString;

    public String getSecret() {
        return secret;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public String getHeaderString() {
        return headerString;
    }
}
