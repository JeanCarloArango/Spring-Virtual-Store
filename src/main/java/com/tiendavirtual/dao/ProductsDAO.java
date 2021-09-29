package com.tiendavirtual.dao;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tiendavirtual.dto.ProductsDTO;

public class ProductsDAO {

	private ConnectionDB con = new ConnectionDB();
	private PreparedStatement sentence;
	private String sql;

	public boolean createProducts(ProductsDTO products) {

		try {
			sql = "INSERT INTO productos (ivacompra, producto, precio_compra, precio_venta, proveedores_id) VALUES (?,?,?,?,?);";
			sentence = this.con.pStimp(sql);
			sentence.setDouble(1, products.getVatPurchase());
			sentence.setString(2, products.getProductName());
			sentence.setDouble(3, products.getPruchasePrice());
			sentence.setDouble(4, products.getSellingPrice());
			// sentence.setString(5, products.);

			Boolean res = false;
			if (!sentence.execute()) {
				res = true;
			}
			this.con.disconnect();
			return res;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	public void fileUpload() {
		try {
			FileReader file = new FileReader("");
			BufferedReader buffer = new BufferedReader(file);
			String line;
			while ((line = buffer.readLine()) != null) {
				String[] tokens = line.replace("\"", "").split(",");
				ProductsDTO z = new ProductsDTO(Double.parseDouble(tokens[0]), tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
				this.createProducts(z);
			}
			buffer.close();
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ProductsDTO searchProducts(String name) {
		ResultSet productsFound = null;

		try {
			sql = "SELECT * FROM productos WHERE producto = ?;";

			sentence = this.con.pStimp(sql);
			sentence.setString(1, name);
			productsFound = sentence.executeQuery();
			ProductsDTO user = null;
			while (productsFound.next()) {
				// String userDni, String userName, String userEmail, String userNick, String
				// userPass
				user = new ProductsDTO(productsFound.getDouble("ivacompra"), productsFound.getString("producto"),
						productsFound.getDouble("precio_compra"), productsFound.getDouble("precio_venta"));
			}
			con.disconnect();
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Boolean updateProducts(ProductsDTO products) {
		try {
			sql = "UPDATE productos SET ivacompra=?, producto=?, precio_compra=?, precio_venta=?, proveedores_id=? WHERE cedula = ?;";
			sentence = this.con.pStimp(sql);
			sentence.setDouble(1, products.getVatPurchase());
			sentence.setString(2, products.getProductName());
			sentence.setDouble(3, products.getPruchasePrice());
			sentence.setDouble(4, products.getSellingPrice());
			// sentence.setString(5, products.);

			Boolean res = false;
			if (!sentence.execute()) {
				res = true;
			}
			this.con.disconnect();
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Boolean delUser(String cedula) {
		try {
			sql = "UPDATE productos SET estado='D' WHERE producto = ?;";
			sentence = this.con.pStimp(sql);
			sentence.setString(1, cedula);

			Boolean res = false;
			if (!sentence.execute()) {
				res = true;
			}
			this.con.disconnect();
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}