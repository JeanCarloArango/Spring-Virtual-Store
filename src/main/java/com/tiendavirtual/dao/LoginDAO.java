package com.tiendavirtual.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
	private ConnectionDB con;
	private PreparedStatement sentence;
	private String sql;
	
	public boolean login(String userNick, String userPass) {
		con = new ConnectionDB();
		try {
			sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ? and estado = 'E';";
			sentence = this.con.pStimp(sql);
			sentence.setString(1, userNick);
			sentence.setString(2, userPass);
			
			ResultSet userFound = sentence.executeQuery();
			
			boolean res = false;
			if (userFound.next()) {				
				res = true;
			}
			userFound.close();
			sentence.close();
			this.con.disconnect();
			return res;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Accesso denegado");
			return false;
		}
	}
}
