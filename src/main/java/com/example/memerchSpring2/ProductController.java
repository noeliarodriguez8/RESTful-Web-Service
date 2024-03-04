package com.example.memerchSpring2;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductRepository repository;

    ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/products-search-name")
    public List<ProductEntity> getProdutsByName(@RequestParam(value = "productName") String productName) {
        return repository.findByProductNameContainsIgnoreCase(productName);
    }

    @GetMapping("/products-search-color")
    public List<ProductEntity> getProdutsByColor(@RequestParam(value = "productColor") String productColor) {
        return repository.findByProductColorContainsIgnoreCase(productColor);
    }
}
