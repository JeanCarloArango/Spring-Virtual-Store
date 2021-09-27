package com.tiendavirtual.dto;

public class CustomerDTO {
    private String identifyCustomer;
    private String addressCustomer;
    private String emailCustomer;
    private String nameCustomer;
    private String phoneCustomer;

	public CustomerDTO(String identifyCustomer, String addressCustomer, String emailCustomer, String nameCustomer,
			String phoneCustomer) {
		super();
		this.identifyCustomer = identifyCustomer;
		this.addressCustomer = addressCustomer;
		this.emailCustomer = emailCustomer;
		this.nameCustomer = nameCustomer;
		this.phoneCustomer = phoneCustomer;
	}

	public String getIdentifyCustomer() {
		return identifyCustomer;
	}
	
	public String getAddressCustomer() {
		return addressCustomer;
	}

	public String getEmailCustomer() {
		return emailCustomer;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public String getPhoneCustomer() {
		return phoneCustomer;
	}
    
}

