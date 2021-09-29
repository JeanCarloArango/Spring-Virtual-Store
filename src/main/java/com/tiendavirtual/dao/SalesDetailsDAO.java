package com.tiendavirtual.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tiendavirtual.dto.SalesDetailsDTO;

public class SalesDetailsDAO {
	private ConnectionDB con = new ConnectionDB();
	private PreparedStatement sentence;
	private String sql;
	
	public Boolean createSalesDetails(SalesDetailsDTO details) {
		try {
			sql = "INSERT INTO detalle_ventas (cod_detalle_venta, cantidad_producto, valor_total, valor_venta, valor_iva, ventas_id, productos_ud) VALUES (?,?,?,?,?,?,?);";
			sentence = this.con.pStimp(sql);
			sentence.setString(1, "cod");
			sentence.setInt(2, details.getProductQuantity());
			sentence.setDouble(3, details.getTotalValue());
			sentence.setDouble(4, details.getSalesValue());
			sentence.setDouble(5, details.getVatValue());
			sentence.setString(6, "ventaId");
			sentence.setString(7, "producID");

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
	
	public SalesDetailsDTO searchSalesDetails(String cedula) {
		ResultSet detailsFound = null;
		
		try {
			sql = "SELECT * FROM detalle_ventas WHERE cedula = ?;";
			sentence.setString(1, cedula);
			
			detailsFound = sentence.executeQuery();
			SalesDetailsDTO customer = null;
			while (detailsFound.next()) {
				customer = new SalesDetailsDTO();
			}
			
			this.con.disconnect();
			return customer;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public Boolean updateSalesDetails(SalesDetailsDAO customer) {
		try {
			sql = "UPDATE detalle_ventas SET cedula=?, direccion=?, email=?, nombre=?, telefono=? where cedula = ?;";
			sentence = this.con.pStimp(sql);
			
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
	
	public Boolean delSalesDetails(String cedula) {
		try {
			sql = "UPDATE detalle_ventas SET estado='D' where cedula = ?;";
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