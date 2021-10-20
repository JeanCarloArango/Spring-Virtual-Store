package com.tiendavirtual.dto;

public class SalesDetailsDTO {

	private String ccCustomer;
	private String nameCustomer;
	private Double totalSale;

	public SalesDetailsDTO(String ccCustomer, String nameCustomer, Double totalSale) {
		super();
		this.ccCustomer = ccCustomer;
		this.nameCustomer = nameCustomer;
		this.totalSale = totalSale;
	}

	public String getCcCustomer() {
		return ccCustomer;
	}


	public String getNameCustomer() {
		return nameCustomer;
	}

	public Double getTotalSale() {
		return totalSale;
	}

}