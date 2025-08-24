package dev.guisandroni.warehouse.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class OpenAPISwagger {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api warehouse Microservice")
                        .version("1.0")
                        .description("Documentação API"));
    }
}
