package com.catalog.web.controller;

import com.catalog.BaseIntegrationTest;
import com.catalog.domain.Product;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@Sql("/test-data.sql")
public class ProductControllerTest  extends BaseIntegrationTest {

    @Test
    void shouldReturnAllProducts() throws Exception{
        given().contentType(ContentType.JSON)
                .when()
                .get("api/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElements", is(15))
                .body("pageNumber", is(1))
                .body("totalPages", is(2))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));
    }

    @Test
    void shouldReturnProductByCode(){
        Product product = given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}","P100")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(Product.class);

        assert product != null;
        assert product.code().equals("P100");
        assert product.name().equals("The Hunger Games");
        assert product.description().equals("Winning will make you famous. Losing means certain death...");
    }

}
