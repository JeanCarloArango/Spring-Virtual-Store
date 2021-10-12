package com.tiendavirtual.dao;

import java.sql.*;

public class ConnectionDB {
	
	/*private String bd = "database_tivi";
	private String login = "root";
	private String password = "admin";
	private String url = "jdbc:mysql://localhost/" + bd;*/
	
	private String bd = "Grupo02BraveTeam";
	private String login = "admin";
	private String password = "MisionTIC2022GRUPO02";
	private String url = "jdbc:mysql://misiontic2022grupo02.czo3ixoe3xoe.us-east-1.rds.amazonaws.com/" + bd;
	
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
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public PreparedStatement pStimp(String sql) {
		try {
			this.sentence = connection.prepareStatement(sql);
			return this.sentence;
		} catch (Exception e) {
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
