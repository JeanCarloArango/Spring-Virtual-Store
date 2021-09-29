package com.tiendavirtual.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tiendavirtual.dto.SalesDetailsDTO;
import com.tiendavirtual.dto.SuppliersDTO;

public class SuppliersDAO {
	private ConnectionDB con = new ConnectionDB();
	private PreparedStatement sentence;
	private String sql;
	
	public Boolean createSupplier(SuppliersDTO supplier) {
		try {
			sql = "INSERT INTO proveedores (nit, ciudad, direccion, nombre, telefono) VALUES (?,?,?,?,?);";
			sentence = this.con.pStimp(sql);
			sentence.setString(1, supplier.getSupplierNit());
			sentence.setString(2, supplier.getSupplierCity());
			sentence.setString(3, supplier.getSupplierAddress());
			sentence.setString(4, supplier.getSupplierName());
			sentence.setString(5, supplier.getSupplierPhone());

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
	
	public SuppliersDTO searchSupplier(String nit) {
		ResultSet supplierFound = null;
		
		try {
			sql = "SELECT * FROM proveedores WHERE nit = ?;";
			sentence.setString(1, nit);
			
			supplierFound = sentence.executeQuery();
			SuppliersDTO supplier = null;
			while (supplierFound.next()) {
				supplier = new SuppliersDTO(supplierFound.getString("nit"), supplierFound.getString("ciudad"), 
						supplierFound.getString("direccion"), supplierFound.getString("nombre"), supplierFound.getString("telefono"));
			}
			this.con.disconnect();
			return supplier;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public Boolean updateSupplier(SuppliersDTO supplier) {
		try {
			sql = "UPDATE proveedores SET nit=?, ciudad=?, direccion=?, nombre=?, telefono=? where nit = ?;";
			sentence = this.con.pStimp(sql);
			sentence.setString(1, supplier.getSupplierNit());
			sentence.setString(2, supplier.getSupplierCity());
			sentence.setString(3, supplier.getSupplierAddress());
			sentence.setString(4, supplier.getSupplierName());
			sentence.setString(5, supplier.getSupplierPhone());
			sentence.setString(6, supplier.getSupplierNit());
			
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
	
	public Boolean delSupplier(String nit) {
		try {
			sql = "UPDATE proveedores SET estado='D' where nit = ?;";
			sentence = this.con.pStimp(sql);
			sentence.setString(1, nit);
			
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