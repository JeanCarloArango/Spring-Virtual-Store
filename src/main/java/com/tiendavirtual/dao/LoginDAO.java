package com.tiendavirtual.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
	private ConnectionDB con = new ConnectionDB();
	private PreparedStatement sentence;
	private String sql;
	
	public boolean login(String userNick, String userPass) {
		try {
			sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?;";
			sentence = this.con.pStimp(sql);
			sentence.setString(1, userNick);
			sentence.setString(2, userPass);
			
			ResultSet userFound = sentence.executeQuery();
			
			boolean res = false;
			while (userFound.next()) {
				this.con.disconnect();
				userFound.close();
				sentence.close();
				return true;
			}
			return res;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Accesso denegado");
			return false;
		}
	}
}
