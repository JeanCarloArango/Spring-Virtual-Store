package com.tiendavirtual.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tiendavirtual.dto.SalesDetailsDTO;

public class SalesDetailsDAO {
	private ConnectionDB con;
	private PreparedStatement sentence;
	private String sql;
	
	public ArrayList<SalesDetailsDTO> searchSalesDetails() {
		con = new ConnectionDB();
		ArrayList<SalesDetailsDTO> customer = new ArrayList<SalesDetailsDTO>();
		
		try {
			sql = "SELECT cus.cedula, cus.nombre, ven.total_venta from ventas ven join clientes cus on ven.clientes_id = cus.id;";
			sentence = this.con.pStimp(sql);
			
			ResultSet detailsFound = sentence.executeQuery();
			while (detailsFound.next()) {
				SalesDetailsDTO details = new SalesDetailsDTO(detailsFound.getString("cedula"),
						detailsFound.getString("nombre"), Double.parseDouble(detailsFound.getString("total_venta")));
				customer.add(details);
			}
			System.out.println(customer);
			detailsFound.close();
			sentence.close();
			this.con.disconnect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return customer;
	}

}