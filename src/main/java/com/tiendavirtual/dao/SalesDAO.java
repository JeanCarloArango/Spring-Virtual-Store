package com.tiendavirtual.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tiendavirtual.dto.SalesDTO;

public class SalesDAO {
	private ConnectionDB con;
	private String sql;
	private PreparedStatement sentence;
	private CustomerDAO cus = new CustomerDAO();
	
	public boolean createSales(SalesDTO sale) {
		con = new ConnectionDB();
		int id = cus.idCustomer(String.valueOf(sale.getCustomer()));
		try {
			sql = "INSERT INTO ventas (ivaventa, total_venta, valor_venta, usuarios_id, clientes_id) VALUES (?,?,?,?,?);";
			sentence = this.con.pStimp(sql);
			sentence.setDouble(1, sale.getIvaSale());
			sentence.setDouble(2, sale.getTotalSale());
			sentence.setDouble(3, sale.getValorFinal());
			sentence.setInt(4, 3);
			sentence.setInt(5, id);
			
			Boolean res = false;
			if (!sentence.execute()) {
				res = true;
			}
			this.sentence.close();
			this.con.disconnect();
			return res;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}
}
