package com.tiendavirtual.dto;

public class SalesDTO {

	private int user;
	private int customer;
	private Double ivaSale;
	private Double totalSale;
	private Double valorFinal;

	public SalesDTO(int user, int customer, Double ivaSale, Double totalSale, Double valorFinal) {
		super();
		this.user = user;
		this.customer = customer;
		this.ivaSale = ivaSale;
		this.totalSale = totalSale;
		this.valorFinal = valorFinal;
	}

	public int getUser() {
		return user;
	}

	public int getCustomer() {
		return customer;
	}

	public Double getIvaSale() {
		return ivaSale;
	}

	public Double getTotalSale() {
		return totalSale;
	}

	public Double getValorFinal() {
		return valorFinal;
	}

}
