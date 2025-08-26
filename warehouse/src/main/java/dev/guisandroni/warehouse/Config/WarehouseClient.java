package dev.guisandroni.warehouse.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
public class WarehouseClient {
    //passar o basepath para o client do warehouse
    //basepath dentro do docker
    @Bean
    RestClient storeClient(@Value("${store.base-path}") final String basePath){
        return RestClient.builder().baseUrl(basePath).build();
        // return RestClient.create(basePath);
    }
}
