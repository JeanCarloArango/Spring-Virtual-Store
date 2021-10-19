package com.tiendavirtual.dao;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tiendavirtual.dto.ProductsDTO;
import com.tiendavirtual.dto.UserDTO;

public class ProductsDAO {

	private ConnectionDB con;
	private PreparedStatement sentence;
	private String sql;

	public boolean createProducts(ProductsDTO products) {

		try {
			con = new ConnectionDB();
			sql = "INSERT INTO productos (ivacompra, producto, precio_compra, precio_venta, proveedores_id) VALUES (?,?,?,?,?);";
			sentence = this.con.pStimp(sql);
			sentence.setDouble(1, products.getVatPurchase());
			sentence.setString(2, products.getProductName());
			sentence.setDouble(3, products.getPruchasePrice());
			sentence.setDouble(4, products.getSellingPrice());
			sentence.setInt(5, products.getIdProveedor());

			Boolean res = false;
			if (!sentence.execute()) {
				res = true;
				con.disconnect();
			}
			return res;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	public void fileUpload(File fileProduct) {
		try {
			FileReader file = new FileReader(fileProduct);
			BufferedReader buffer = new BufferedReader(file);
			String line;
			while ((line = buffer.readLine()) != null) {
				String[] tokens = line.split(";");
				ProductsDTO z = new ProductsDTO(Double.parseDouble(tokens[0]), tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]));
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

	public ArrayList<ProductsDTO> searchProducts(String name) {
		con = new ConnectionDB();
		ArrayList<ProductsDTO> products = new ArrayList<ProductsDTO>();

		try {
			//sql = "SELECT pro.*, prove.nombre from productos pro join proveedores prove on pro.proveedores_id = prove.id;";
			sql = "SELECT * FROM productos WHERE producto = ? AND estado = 'E';";

			sentence = this.con.pStimp(sql);
			sentence.setString(1, name);
			ResultSet productsFound = sentence.executeQuery();
			
			while (productsFound.next()) {
				ProductsDTO produt = new ProductsDTO(productsFound.getDouble("ivacompra"), productsFound.getString("producto"),
						productsFound.getDouble("precio_compra"), productsFound.getDouble("precio_venta"), productsFound.getInt("proveedores_id"));
				products.add(produt);
			}
			con.disconnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	public Boolean updateProducts(ProductsDTO products) {
		con = new ConnectionDB();
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
		con = new ConnectionDB();
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