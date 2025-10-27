package com.catalog.web.controller;

import com.catalog.BaseIntegrationTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
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
//        given().contentType(ContentType.JSON)
//                .when()
//                .get("api/products/{code}","P100")
//                .statusCode(200);

    }

}
