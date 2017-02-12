package ru.sbt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ru.sbt.parser.Parser;

@Configuration
public class ApplicationConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("ru.sbt.xml");
        return marshaller;
    }

    @Bean
    public Parser parser() {
        return new Parser();
    }
}
