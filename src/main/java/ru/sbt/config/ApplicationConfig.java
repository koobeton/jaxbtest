package ru.sbt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.xml.Application;

@Configuration
public class ApplicationConfig {

    @Bean
    public Application application() {
        return new Application();
    }
}
