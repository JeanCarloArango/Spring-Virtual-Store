package com.tiendavirtual.dao;

import java.sql.*;

public class ConnectionDB {
	
	private String bd = "database_tivi";
	private String login = "root";
	private String password = "admin";
	private String url = "jdbc:mysql://localhost/" + bd;
	
	private Connection connection = null;
	private PreparedStatement sentence;
	
	public ConnectionDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, login, password);
			if (connection != null) {
				System.out.println("Conexion a base de datos " + bd + " OK\n");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public PreparedStatement pStimp(String sql) {
		try {
			this.sentence = connection.prepareStatement(sql);
			return this.sentence;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public void disconnect() {
		try {
			connection.close();
			System.out.println("Desconectado");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
