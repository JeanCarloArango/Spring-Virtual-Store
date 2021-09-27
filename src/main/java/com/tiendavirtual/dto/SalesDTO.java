package com.tiendavirtual.dto;

public class SalesDTO {

  private Double vatSale;
  private Double totalSale;
  private Double salesValue;

  public SalesDTO() {}

  public SalesDTO(Double vatSale, Double totalSale, Double salesValue) {
    this.vatSale = vatSale;
    this.totalSale = totalSale;
    this.salesValue = salesValue;
  }

  public Double getVatSale() {
    return vatSale;
  }

  public Double getTotalSale() {
    return totalSale;
  }

  public Double getSalesValue() {
    return salesValue;
  }
 
}
