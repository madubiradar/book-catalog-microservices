package com.catalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ProblemDetail handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );

        problemDetail.setTitle("Product Not Found");
        problemDetail.setType(URI.create("http://localhost:8081/catalog-service"));
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("service","catalog-service");
        return problemDetail;

    }

    @ExceptionHandler(value = {Exception.class})
    public ProblemDetail handleGenericException(ProductNotFoundException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage()
        );

        problemDetail.setTitle("Internal Server Error");
        problemDetail.setType(URI.create("http://localhost:8081/catalog-service"));
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("service","catalog-service");
        return problemDetail;

    }
}
