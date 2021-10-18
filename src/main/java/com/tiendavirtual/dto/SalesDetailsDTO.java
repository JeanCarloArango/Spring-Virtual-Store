package com.tiendavirtual.dto;

public class SalesDetailsDTO {

	private String ccCustomer;
	private String nameCustomer;
	private Double totalSale;

	public SalesDetailsDTO(String ccUser, String nameUser, Double totalSale) {
		super();
		this.ccCustomer = ccUser;
		this.nameCustomer = nameUser;
		this.totalSale = totalSale;
	}

	public String getCcUser() {
		return ccCustomer;
	}

	public String getNameUser() {
		return nameCustomer;
	}

	public Double getTotalSale() {
		return totalSale;
	}

}