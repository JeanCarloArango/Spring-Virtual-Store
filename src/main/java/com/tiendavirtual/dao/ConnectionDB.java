package com.tiendavirtual.dao;

import java.sql.*;

public class ConnectionDB {
	
	private String bd = "database_tivi";
	private String login = "root";
	private String password = "admin";
	private String url = "jdbc:mysql://localhost/" + bd;
	
	private Connection connection = null;
	
	public Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, login, password);
			if (connection != null) {
				System.out.println("Conexion a base de datos " + bd + " OK\n");
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
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
