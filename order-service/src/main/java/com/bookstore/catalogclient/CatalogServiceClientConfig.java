package com.bookstore.catalogclient;

import com.bookstore.config.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class CatalogServiceClientConfig {

    @Bean
    RestClient restClient(ApplicationProperties applicationProperties) {

        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory();// connection timeout
        requestFactory.setReadTimeout(Duration.ofSeconds(5));    // read timeout

        return RestClient.builder()
                .baseUrl(applicationProperties.catalogServiceUrl())
                .requestFactory(requestFactory)
                .build();

    }
}
