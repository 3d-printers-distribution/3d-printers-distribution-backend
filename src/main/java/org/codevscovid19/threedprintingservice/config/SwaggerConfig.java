package org.codevscovid19.threedprintingservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi swaggerApi() {
        return GroupedOpenApi.builder()
                .packagesToScan("org.codevscovid19.threedprintingservice.rest")
                .setGroup("frontend-api")
                .build();
    }

    @Bean
    public OpenAPI threedprintingApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("3D-Livery")
                        .description("Backend for the 3D-Livery project")
                );
    }
}
