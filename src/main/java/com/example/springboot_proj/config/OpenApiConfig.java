package com.example.springboot_proj.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Fleet Management API",
                version = "v1",
                description = "API de gestion de flotte: vehicules, chauffeurs, missions, consommations, maintenance et dashboard.",
                contact = @Contact(name = "Equipe Fleet")
        ),
        servers = {
                @Server(url = "http://localhost:8081", description = "Environnement local")
        }
)
public class OpenApiConfig {
}

