package com.bilvantis.user.app.service.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFoxConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Rewards and Recognition APIs")
                .description("Rewards and Recognition APIs for post,get,put,delete")
                .version("1.0")
                .contact(new Contact().name("Bilvantis").email("Bilvantis.Hr@Bilvantis.io"))

        ).externalDocs(new ExternalDocumentation().url("https://rewards-frontend-sskn24q3sq-uc.a.run.app/")
                .description("Rewards deployed url"));

    }
}