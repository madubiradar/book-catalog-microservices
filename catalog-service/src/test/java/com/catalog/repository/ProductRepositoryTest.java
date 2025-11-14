package com.catalog.repository;

import com.catalog.domain.ProductEntity;
import com.catalog.domain.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DataJpaTest(
        properties = {
                "spring.test.database.replace=none",
//                "spring.datasource.url=jdbc:tc:postgres:16-alpine:///db",
        }
)
@Import(TestcontainersConfiguration.class)
@Sql("/test-data.sql")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productorRepository;

    @Test
    void shouldGetAllProducts() {

        List<ProductEntity> products = productorRepository.findAll();
        assert !products.isEmpty();
        assert products.size() == 15;

    }

    @Test
    void shouldGetProductByCode() {
        ProductEntity product = productorRepository.findByCode("P100").orElseThrow();
        assert product.getCode().equals("P100");
        assert product.getName().equals("The Hunger Games");
        assert product.getPrice().compareTo(BigDecimal.valueOf(34)) == 0;
    }

    @Test
    void shouldReturnEmptyWhenCodeNotExists() {
        Optional<ProductEntity> product = productorRepository.findByCode("invalid_code");
        assert product.isEmpty();

    }
}
