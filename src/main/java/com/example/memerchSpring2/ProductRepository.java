package com.example.memerchSpring2;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findByProductNameContainsIgnoreCase(String productName);
  
    List<ProductEntity> findByProductColorContainsIgnoreCase(String productColor);
  
    List<ProductEntity> findByProductNameContainsOrProductColorContainsAllIgnoreCase(String productName,
      String productColor);
  
  }