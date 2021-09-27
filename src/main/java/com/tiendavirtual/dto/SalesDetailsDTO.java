package com.tiendavirtual.dto;

public class SalesDetailsDTO {
  
  private int productQuantity;
  private Double totalValue;
  private Double salesValue;
  private Double vatValue;
  
  public SalesDetailsDTO() {}

  public SalesDetailsDTO(int productQuantity, Double totalValue, Double salesValue, Double vatValue) {
    this.productQuantity = productQuantity;
    this.totalValue = totalValue;
    this.salesValue = salesValue;
    this.vatValue = vatValue;
  }

  public int getProductQuantity() {
    return productQuantity;
  }

  public Double getTotalValue() {
    return totalValue;
  }

  public Double getSalesValue() {
    return salesValue;
  }

  public Double getVatValue() {
    return vatValue;
  }

}