package ua.aleksanid.maindomain.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ua.aleksanid.maindomain.util.YamlPropertySourceFactory;

@Configuration
@PropertySource(value = "classpath:datasource.yml", factory = YamlPropertySourceFactory.class)
public class DataSourceConfiguration {

}
