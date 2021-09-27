package com.tiendavirtual.dto;

public class ProductsDTO {

  private Double vatPurchase;
  private String productName;
  private Double pruchasePrice;
  private Double sellingPrice;

  public ProductsDTO() {}

  public ProductsDTO(Double vatPurchase, String productName, Double pruchasePrice, Double sellingPrice) {
    this.vatPurchase = vatPurchase;
    this.productName = productName;
    this.pruchasePrice = pruchasePrice;
    this.sellingPrice = sellingPrice;
  }

  public Double getVatPurchase() {
    return vatPurchase;
  }

  public String getProductName() {
    return productName;
  }

  public Double getPruchasePrice() {
    return pruchasePrice;
  }

  public Double getSellingPrice() {
    return sellingPrice;
  }
  
}