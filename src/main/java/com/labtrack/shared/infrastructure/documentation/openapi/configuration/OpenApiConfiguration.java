package com.labtrack.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI labtrackOpenApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("LabTrack API")
                        .description("REST API para gestión de equipos de laboratorio y mantenimiento.")
                        .version("v1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Desarrollo Local"),
                        new Server().url("https://labtrack-backend-services-b6d4aahpbhf5etfz.azurewebsites.net").description("Producción Azure")
                ))
                .externalDocs(new ExternalDocumentation()
                        .description("LabTrack Documentation Repository")
                        .url("https://github.com/your-org/labtrack"));
    }
}