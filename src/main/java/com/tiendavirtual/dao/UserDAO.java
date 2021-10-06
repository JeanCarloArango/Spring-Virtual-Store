package com.tiendavirtual.dao;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.tiendavirtual.dto.UserDTO;

public class UserDAO {
	
	private ConnectionDB con = new ConnectionDB();
	private PreparedStatement sentence;
	private String sql;
	
	public boolean createUser(UserDTO user) {

		try {
			sql = "INSERT INTO usuarios (cedula, email, nombre, password, usuario) VALUES (?,?,?,?,?);";
			sentence = this.con.pStimp(sql);
			sentence.setString(1, user.getUserDni());
			sentence.setString(2, user.getUserEmail());
			sentence.setString(3, user.getUserName());
			sentence.setString(4, user.getUserPass());
			sentence.setString(5, user.getUserNick());
			
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
	
	public ArrayList<UserDTO> searchUser(String cedula) {
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();

		try {
			sql = "SELECT * FROM usuarios WHERE cedula = ? AND estado = 'E';";
			sentence = this.con.pStimp(sql);
			sentence.setString(1, cedula);
			
			ResultSet userFound = sentence.executeQuery();
			
			while (userFound.next()) {
				UserDTO us = new UserDTO(userFound.getString("cedula"), userFound.getString("nombre"),
						userFound.getString("email"), userFound.getString("usuario"), userFound.getString("password"));
				users.add(us);
			}
			userFound.close();
			sentence.close();
			this.con.disconnect();
			JOptionPane.showMessageDialog(null, "Usuario encontrado");
		} catch (Exception e) {
			System.out.println(e);
		}
		return users;
	}

	public Boolean updateUser(UserDTO user) {
		try {
			sql = "UPDATE usuarios SET cedula=?, email=?, nombre=?, password=?, usuario=? WHERE cedula = ?;";
			sentence = this.con.pStimp(sql);
			sentence.setString(1, user.getUserDni());
			sentence.setString(2, user.getUserEmail());
			sentence.setString(3, user.getUserName());
			sentence.setString(4, user.getUserPass());
			sentence.setString(5, user.getUserNick());
			sentence.setString(6, user.getUserDni());
			
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
			sql = "UPDATE usuarios SET estado='D' WHERE cedula = ?;";
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