package com.accenture.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Configuration OpenAPI pour l'application Location des Véhicules.
 * Cette classe configure Swagger pour l'application
 */
@Configuration
public class ConfigurationOpenApi {
    /**
     * Cette méthode crée une instance d'OpenAPI avec les informations générales sur l'API, comme le titre,
     * la description, la version et les informations de contact.
     *
     * @return une instance d'OpenAPI configurée avec les informations de l'application.
     */
    @Bean
    public OpenAPI apiConfiguration() {
        return new OpenAPI()
                .info(new Info()
                        .title("Location des Véhicules")
                        .description("Api pour l'application Location des Véhicules")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("Natasha")
                                .email("natallia.krautsova@accenture.com")));
    }
}


