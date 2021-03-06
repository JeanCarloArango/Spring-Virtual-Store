package com.tiendavirtual.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tiendavirtual.dto.SuppliersDTO;

public class SuppliersDAO {
	private ConnectionDB con;
	private PreparedStatement sentence;
	private String sql;
	
	public Boolean createSupplier(SuppliersDTO supplier) {
		con = new ConnectionDB();
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
	
	public ArrayList<SuppliersDTO> searchSupplier(String nit) {
		con = new ConnectionDB();
		ArrayList<SuppliersDTO> suplierAr = new ArrayList<SuppliersDTO>();
		
		try {
			sql = "SELECT * FROM proveedores WHERE nit = ?;";
			sentence = this.con.pStimp(sql);
			sentence.setString(1, nit);
			
			ResultSet supplierFound = sentence.executeQuery();
			while (supplierFound.next()) {
				SuppliersDTO supplier = new SuppliersDTO(supplierFound.getString("nit"), supplierFound.getString("ciudad"), 
						supplierFound.getString("direccion"), supplierFound.getString("nombre"), supplierFound.getString("telefono"));
				suplierAr.add(supplier);
			}
			supplierFound.close();
			sentence.close();
			this.con.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return suplierAr;
	}
	
	public Boolean updateSupplier(SuppliersDTO supplier) {
		con = new ConnectionDB();
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
		con = new ConnectionDB();
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