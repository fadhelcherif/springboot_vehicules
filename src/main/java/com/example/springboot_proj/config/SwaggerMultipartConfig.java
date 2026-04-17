package com.example.springboot_proj.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration pour améliorer l'affichage des uploads multipart dans Swagger.
 * Enregistre les schémas de fichier pour les uploads.
 */
@Configuration
public class SwaggerMultipartConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSchemas("FileUpload", new Schema<>()
                                .type("object")
                                .addProperty("file", new Schema<>()
                                        .type("string")
                                        .format("binary")
                                        .description("Fichier à uploader")
                                )
                        )
                );
    }
}

