package com.tiendavirtual.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tiendavirtual.dto.CustomerDTO;
import com.tiendavirtual.dto.UserDTO;

public class CustomerDAO {
	
	ConnectionDB con = null;
	CustomerDTO customer = null;
	//String userDni, String userName, String userEmail, String userNick, String userPass
	public void createCustomer(CustomerDTO customer) {

		con = new ConnectionDB();

		try {
			Statement stmt = con.getConnection().createStatement();
			String sql = "INSERT INTO clientes(cedula, nombre, direccion, telefono, email) VALUES ('"
					+ customer.getIdentifyCustomer() + "','" + customer.getNameCustomer() + "','" + customer.getAddressCustomer() + "','"
					+ customer.getPhoneCustomer() + "','" + customer.getEmailCustomer() + "');";
			stmt.executeUpdate(sql);
			con.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CustomerDTO searchUser(String cedula) {
		con = new ConnectionDB();
		ResultSet customerFound = null;
		
		try {
			Statement stmt = con.getConnection().createStatement();
			customerFound = stmt.executeQuery("SELECT * FROM clientes WHERE cedula = '"+ cedula +"';");
			while (customerFound.next()) {
				customer = new CustomerDTO(customerFound.getString("cedula"), customerFound.getString("nombre"),
						customerFound.getString("direccion"), customerFound.getString("telefono"), customerFound.getString("email"));
			}
			System.out.println("Encontrado");
			con.disconnect();
			return customer;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void updateUser(CustomerDTO customer) {
		try {
			
			Statement stmt = con.getConnection().createStatement();
			stmt.executeUpdate("UPDATE clientes SET (cedula, nombre, direccion, telefono, email) VALUES ('"
					+ customer.getIdentifyCustomer() + "','" + customer.getNameCustomer() + "','" + customer.getAddressCustomer() + "','"
					+ customer.getPhoneCustomer() + "','" + customer.getEmailCustomer() + "' WHERE cedula = '"+ customer.getIdentifyCustomer() +"');");
			con.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delUser(String cedula) {
		try {
			Statement stmt = con.getConnection().createStatement();
			stmt.executeUpdate("UPDATE FROM clientes SET estado = D WHERE cedula = '"+ cedula +"';");
			con.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}