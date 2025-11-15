package com.catalog.web.controllers;

import com.catalog.domain.PagedResult;
import com.catalog.domain.Product;
import com.catalog.domain.ProductEntity;
import com.catalog.domain.ProductService;
import com.catalog.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/products")
class ProductController {

    private ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo){
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) throws InterruptedException {
        log.info("Get product by code from catalog{}", code);
        return productService.getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new ProductNotFoundException("Product Not Found with code: " +code));

    }
}
