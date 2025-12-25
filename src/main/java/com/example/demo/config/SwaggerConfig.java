package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(List.of(
                        new Server()
                                .url("https://9096.32procr.amypo.ai/")
                                .description("Local Development Server")
                ))
                .info(new Info()
                        .title("Hostel Roommate Compatibility Matcher API")
                        .description("API for matching hostel roommates based on compatibility")
                        .version("1.0"));
    }
}
