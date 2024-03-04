package com.example.memerchSpring2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class ProductEntity {

  private @Id @GeneratedValue Integer productID;
  private String productName;
  private String productColor;
  private String productTypeID;
  private Integer stock;
  private Double price;

  protected ProductEntity() {
    this(null, null, null, null, null);
  }

  ProductEntity(String productName, String productColor, String productTypeID, Integer stock, Double price) {
    this.productID = null;
    this.productName = productName;
    this.productColor = productColor;
    this.productTypeID = productTypeID;
    this.stock = stock;
    this.price = price;
  }

  public Integer getProductID() {
    return productID;
  }

  public void setProductID(Integer productID) {
    this.productID = productID;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductColor() {
    return productColor;
  }

  public void setProductColor(String productColor) {
    this.productColor = productColor;
  }

  public String getProductTypeID() {
    return productTypeID;
  }

  public void setProductTypeID(String productTypeID) {
    this.productTypeID = productTypeID;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
