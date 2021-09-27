package com.tiendavirtual.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tiendavirtual.dto.UserDTO;


public class UserDAO {
	
	ConnectionDB con = null;
	UserDTO user = null;
	
	public void createUser(UserDTO user) {

		con = new ConnectionDB();

		try {
			Statement stmt = con.getConnection().createStatement();
			stmt.executeUpdate("INSERT INTO usuarios(cedula, nombre, email, usuario, password) VALUES ('"
					+ user.getUserDni() + "','" + user.getUserName() + "','" + user.getUserEmail() + "','"
					+ user.getUserNick() + "','" + user.getUserPass() + "');");
			con.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public UserDTO searchUser(String cedula) {
		con = new ConnectionDB();
		ResultSet userFound = null;
		
		try {
			Statement stmt = con.getConnection().createStatement();
			userFound = stmt.executeQuery("SELECT * FROM usuarios WHERE cedula_usuario = '"+ cedula +"';");
			while (userFound.next()) {
				user = new UserDTO(userFound.getString("cedula_usuario"), userFound.getString("nombre_usuario"),
						userFound.getString("email_usuario"), userFound.getString("usuario"), userFound.getString("password"));
			}
			System.out.println("Encontrado");
			con.disconnect();
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void updateUser(UserDTO user) {
		try {
			
			Statement stmt = con.getConnection().createStatement();
			stmt.executeUpdate("UPDATE usuarios SET (cedula_usuario, nombre_usuario, email_usuario, usuario, password) VALUES ('"
					+ user.getUserDni() + "','" + user.getUserName() + "','" + user.getUserEmail() + "','"
					+ user.getUserNick() + "','" + user.getUserPass() + "' WHERE cedula_usuario = '"+ user.getUserDni() +"');");
			con.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delUser(String cedula) {
		try {
			Statement stmt = con.getConnection().createStatement();
			stmt.executeUpdate("DELETE FROM usuarios WHERE cedula_usuario = '"+ cedula +"';");
			con.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}