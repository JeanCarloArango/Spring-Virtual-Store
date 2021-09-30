package com.tiendavirtual.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tiendavirtual.dto.CustomerDTO;

public class CustomerDAO {
	
	private ConnectionDB con = new ConnectionDB();
	private PreparedStatement sentence;
	private String sql;
	
	public Boolean createCustomer(CustomerDTO customer) {
		try {
			sql = "INSERT INTO clientes (cedula, direccion, email, nombre, telefono) VALUES (?,?,?,?,?);";
			sentence = this.con.pStimp(sql);
			sentence.setString(1, customer.getIdentifyCustomer());
			sentence.setString(2, customer.getAddressCustomer());
			sentence.setString(3, customer.getEmailCustomer());
			sentence.setString(4, customer.getNameCustomer());
			sentence.setString(5, customer.getPhoneCustomer());

			Boolean res = false;
			if (!sentence.execute()) {
				res = true;
			}
			return res;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}
	
	public ArrayList<CustomerDTO> searchCustomer(String cedula) {
		ArrayList<CustomerDTO> custoAr = new ArrayList<CustomerDTO>();
		
		try {
			sql = "SELECT * FROM clientes WHERE cedula = ?;";
			sentence = this.con.pStimp(cedula);
			sentence.setString(1, cedula);
			
			ResultSet customerFound = sentence.executeQuery();
			while (customerFound.next()) {
				CustomerDTO customer = new CustomerDTO(customerFound.getString("cedula"), customerFound.getString("direccion"), 
						customerFound.getString("email"), customerFound.getString("nombre"), customerFound.getString("telefono"));
				custoAr.add(customer);
			}
			customerFound.close();
			sentence.close();
			this.con.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return custoAr;
	}
	
	public Boolean updateCustomer(CustomerDTO customer) {
		try {
			sql = "UPDATE clientes SET cedula=?, direccion=?, email=?, nombre=?, telefono=? where cedula = ?;";
			sentence = this.con.pStimp(sql);
			sentence.setString(1, customer.getIdentifyCustomer());
			sentence.setString(2, customer.getAddressCustomer());
			sentence.setString(3, customer.getEmailCustomer());
			sentence.setString(4, customer.getNameCustomer());
			sentence.setString(5, customer.getPhoneCustomer());
			sentence.setString(6, customer.getIdentifyCustomer());
			
			Boolean res = false;
			if (!sentence.execute()) {
				res = true;
			}
			this.con.disconnect();
			return res;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean delCustomer(String cedula) {
		try {
			sql = "UPDATE clientes SET estado='D' where cedula = ?;";
			sentence = this.con.pStimp(sql);
			sentence.setString(1, cedula);
			
			Boolean res = false;
			if (!sentence.execute()) {
				res = true;
			}
			this.con.disconnect();
			return res;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}