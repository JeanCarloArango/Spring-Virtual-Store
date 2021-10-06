package com.tiendavirtual.dto;

public class ProductsDTO {

	private Double vatPurchase;
	private String productName;
	private Double pruchasePrice;
	private Double sellingPrice;
	private int idProveedor;

	public ProductsDTO(Double vatPurchase, String productName, Double pruchasePrice, Double sellingPrice,
			int idProveedor) {
		this.vatPurchase = vatPurchase;
		this.productName = productName;
		this.pruchasePrice = pruchasePrice;
		this.sellingPrice = sellingPrice;
		this.idProveedor = idProveedor;
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

	public int getIdProveedor() {
		return idProveedor;
	}

}