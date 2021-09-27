package com.uelbosque.tiendavirtual;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendavirtual.dao.UserDAO;
import com.tiendavirtual.dto.UserDTO;

@RestController
public class TiendaVirtualControlador {
	
	@RequestMapping("/crearUsuario")
	public String InsertarCliente(UserDTO nUser) {
		UserDAO uDao = new UserDAO();
		uDao.createUser(nUser);
		return "Microservicio de insersión de clientes";
	}
	
	@RequestMapping("/buscarCliente")
	public String ConsultarCliente() {
		return "Microservicio de consulta de clientes";
	}
	
}