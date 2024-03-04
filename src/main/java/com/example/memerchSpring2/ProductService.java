package com.example.memerchSpring2;

// import jakarta.annotation.PostConstruct;

import java.util.*;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductService {

  private final ProductRepository repository;

  // private List<NewProduct> products = List.of( //
  // new NewProduct("Need HELP with your SPRING BOOT 3 App?"), //
  // new NewProduct("Don't do THIS to your own CODE!"), //
  // new NewProduct("SECRETS to fix BROKEN CODE!"));

  public ProductService(ProductRepository repository) {
    this.repository = repository;
  }

  public List<ProductEntity> getProducts() {
    return repository.findAll();
  }

  public ProductEntity create(NewProduct newProduct) {
    return repository.saveAndFlush(new ProductEntity(newProduct.productName(), newProduct.productColor(),
        newProduct.productTypeID(), newProduct.stock(), newProduct.price()));
  }

  public List<ProductEntity> search(ProductSearch productSearch) {
    if (StringUtils.hasText(productSearch.productName()) //
        && StringUtils.hasText(productSearch.productColor())) {
      return repository.findByProductNameContainsOrProductColorContainsAllIgnoreCase(productSearch.productName(),
          productSearch.productColor());
    }
    if (StringUtils.hasText(productSearch.productName())) {
      return repository.findByProductNameContainsIgnoreCase(productSearch.productName());
    }
    if (StringUtils.hasText(productSearch.productColor())) {
      return repository.findByProductColorContainsIgnoreCase(productSearch.productColor());
    }
    return Collections.emptyList();
  }

  public List<ProductEntity> search(UniversalSearch search) {
    ProductEntity probe = new ProductEntity();
    if (StringUtils.hasText(search.value())) {
      probe.setProductName(search.value());
      probe.setProductColor(search.value());
    }
    Example<ProductEntity> example = Example.of(probe, //
        ExampleMatcher.matchingAny() //
            .withIgnoreCase() //
            .withStringMatcher(StringMatcher.CONTAINING));
    return repository.findAll(example);
  }

  // @PostConstruct
  // void initDatabase() {
  // repository.save(new ProductEntity("Producto 1: nombre producto 1",
  // "Product Color 1",
  // "Product Type ID 1",
  // 111,
  // 345.1));

  // repository.save(new ProductEntity("Producto 2: nombre producto 2",
  // "Product Color 2",
  // "Product Type ID 2",
  // 222,
  // 345.2));

  // repository.save(new ProductEntity("Producto 2: nombre producto 2",
  // "Product Color 3",
  // "Product Type ID 3",
  // 333,
  // 345.3));
  // }
}