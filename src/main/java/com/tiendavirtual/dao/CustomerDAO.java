package com.tiendavirtual.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tiendavirtual.dto.CustomerDTO;

public class CustomerDAO {
	
	private ConnectionDB con;
	private PreparedStatement sentence;
	private String sql;
	
	public Boolean createCustomer(CustomerDTO customer) {
		con = new ConnectionDB();
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
			System.out.println(e);
			return false;
		}
	}
	
	public ArrayList<CustomerDTO> searchCustomer(String cedula) {
		con = new ConnectionDB();
		ArrayList<CustomerDTO> custoAr = new ArrayList<CustomerDTO>();
		
		try {
			if (cedula == "") {
				sql = "SELECT * FROM clientes;";
				sentence = this.con.pStimp(sql);
			}else {
				sql = "SELECT * FROM clientes WHERE cedula = ?;";
				sentence = this.con.pStimp(sql);
				sentence.setString(1, cedula);
			}
			
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
	//Method return id customer
	public int idCustomer(String ced) {
		con = new ConnectionDB();
		int id = 0;
		
		try {
			sql = "SELECT id FROM clientes WHERE cedula = ?;";
			sentence = this.con.pStimp(sql);
			sentence.setString(1, ced);
			
			ResultSet customerFound = sentence.executeQuery();
			while (customerFound.next()) {
				return id = customerFound.getInt("id");
			}
			customerFound.close();
			sentence.close();
			this.con.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public Boolean updateCustomer(CustomerDTO customer) {
		con = new ConnectionDB();
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
		con = new ConnectionDB();
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