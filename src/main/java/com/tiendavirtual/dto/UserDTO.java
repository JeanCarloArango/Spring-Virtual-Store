package com.tiendavirtual.dto;

public class UserDTO {
	
	private String userDni;
    private String userName;
    private String userEmail;
    private String userNick;
    private String userPass;

	public UserDTO(String userDni, String userName, String userEmail, String userNick, String userPass) {
		super();
		this.userDni = userDni;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userNick = userNick;
		this.userPass = userPass;
	}

	public String getUserDni() {
		return userDni;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserNick() {
		return userNick;
	}

	public String getUserPass() {
		return userPass;
	}
    
}