package com.micro.managertravel.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Documentacion | Manager Travel")
                        .version("1.0.0")
                        .description("some")
                        .contact(new Contact()
                                .name("Soporte Técnico")
                                .email("soporte@midominio.com")));
    }
}