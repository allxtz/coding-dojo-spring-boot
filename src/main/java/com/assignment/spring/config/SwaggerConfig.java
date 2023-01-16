package com.assignment.spring.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userApi() {
        final String[] packagesToScan = {"com.assignment.spring.controllers"};
        return GroupedOpenApi
                .builder()
                .group("API")
                .packagesToScan(packagesToScan)
                .pathsToMatch("/**")
                .addOpenApiCustomiser(statusApiCostumizer())
                .build();
    }

    private OpenApiCustomiser statusApiCostumizer() {
        return openAPI -> openAPI
                .info(new Info()
                        .title("OpenWeather APP")
                        .description("API")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("DEV Team")
                                .url("www.devTeam.com")
                                .email("contact@devteam.com")));
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Contact Application API").description(
                        "api"));
    }
}
