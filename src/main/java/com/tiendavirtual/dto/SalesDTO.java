package com.tiendavirtual.dto;

public class SalesDTO {

	private int customer;
	private Double ivaSale;
	private Double totalSale;
	private Double valorFinal;

	public SalesDTO(int customer, Double ivaSale, Double totalSale, Double valorFinal) {
		super();
		this.customer = customer;
		this.ivaSale = ivaSale;
		this.totalSale = totalSale;
		this.valorFinal = valorFinal;
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
