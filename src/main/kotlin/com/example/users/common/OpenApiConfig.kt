package com.example.users.common

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(Info().title("My API")
                .version("v1")
                .description("This is a sample Spring Boot API service using springdoc-openapi and OpenAPI 3."))
    }
}
