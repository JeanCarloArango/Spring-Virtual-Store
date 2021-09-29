package com.tiendavirtual.dao;

import java.sql.*;

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

	public UserDTO searchUser(String cedula) {
		ResultSet userFound = null;
		
		try {
<<<<<<< HEAD
			Statement stmt = con.getConnection().createStatement();
			userFound = stmt.executeQuery("SELECT * FROM usuarios WHERE cedula_usuario = '"+ cedula +"';");
=======
			sql = "SELECT * FROM usuarios WHERE cedula = ?;";
			
			sentence = this.con.pStimp(sql);
			sentence.setString(1, cedula);
			userFound = sentence.executeQuery();
			UserDTO user = null;
>>>>>>> branch 'master' of https://github.com/JeanCarloArango/Spring-Virtual-Store.git
			while (userFound.next()) {
				//String userDni, String userName, String userEmail, String userNick, String userPass
				user = new UserDTO(userFound.getString("cedula"), userFound.getString("nombre"),
						userFound.getString("email"), userFound.getString("usuario"), userFound.getString("password"));
			}
			System.out.println("Encontrado: "+ user.getUserDni() + user.getUserName() + user.getUserEmail()+ 
					user.getUserNick() + user.getUserPass());
			con.disconnect();
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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