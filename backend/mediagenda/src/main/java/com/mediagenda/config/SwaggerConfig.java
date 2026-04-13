package com.mediagenda.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MediAgenda - Sistema de Gestión de Citas Médicas")
                        .description("API REST para administrar citas médicas. " +
                                "Permite crear, consultar, actualizar y eliminar citas " +
                                "de forma eficiente y organizada.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo GT01 - UES Occidente")
                                .email("equipo.gt01@ues.edu.sv")));
    }
}
