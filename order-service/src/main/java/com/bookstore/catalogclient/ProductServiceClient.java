package com.bookstore.catalogclient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Slf4j
@Component
public class ProductServiceClient {

    final RestClient  restClient;
    public ProductServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @CircuitBreaker(name = "catalog-service")
    @Retry(name = "catalog-service", fallbackMethod = "getProductByCodeFallBack")
    public Optional<Product> getProductByCode(String code){

        var product = restClient.get()
                .uri("/api/products/{code}", code)
                .retrieve()
                .body(Product.class);

        return Optional.ofNullable(product);
    }

    Optional<Product> getProductByCodeFallBack(String code, Throwable throwable){
        log.info("getProductByCodeFallBack: for code {}", code);
        return Optional.empty();
    }
}
