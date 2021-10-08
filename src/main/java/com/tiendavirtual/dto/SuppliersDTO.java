package com.tiendavirtual.dto;

public class SuppliersDTO {
  
  private String supplierNit;
  private String supplierCity;
  private String supplierAddress;
  private String supplierName;
  private String supplierPhone;

  public SuppliersDTO(String supplierNit, String supplierCity, String supplierAddress, String supplierName, String supplierPhone) {
	this.supplierNit = supplierNit;
    this.supplierCity = supplierCity;
    this.supplierAddress = supplierAddress;
    this.supplierName = supplierName;
    this.supplierPhone = supplierPhone;
  }
  
  public String getSupplierNit() {
	  return supplierNit;
  }

  public String getSupplierCity() {
    return supplierCity;
  }

  public String getSupplierAddress() {
    return supplierAddress;
  }

  public String getSupplierName() {
    return supplierName;
  }

  public String getSupplierPhone() {
    return supplierPhone;
  }

}