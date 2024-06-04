package com.riwi.PruebaDesempeno.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API para administrar respuestas de usuarios, preguntas formuladas y encuestas realizadas",
        version = "1.0",
        description = "Documentacion API de administrar encuestas en riwiw"
    ))
public class OpenApiConfig {
    
}
