package com.catalog.domain;

import com.catalog.web.controllers.ApplicationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ApplicationProperties applicationProperties;

    ProductService(ProductRepository productRepository, ApplicationProperties applicationProperties) {
        this.productRepository = productRepository;
        this.applicationProperties = applicationProperties;
    }

    public PagedResult<Product> getProducts(int pageNo) {

        pageNo = pageNo <= 1 ? 0 : pageNo -1;
        Pageable pageable = PageRequest.of(pageNo, applicationProperties.pageSize());

        Page<Product> page = productRepository.findAll(pageable)
                .map(ProductMapper::toProduct);

        PagedResult<Product> pagedResult = new PagedResult(
                page.getContent(),
                page.getTotalElements(),
                page.getNumber() + 1,
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious()
        );

        return pagedResult;

    }

    public Optional<Product> getProductByCode(String code) {
        return productRepository.findByCode(code)
                .map(ProductMapper::toProduct);
    }
}
