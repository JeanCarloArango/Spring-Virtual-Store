package com.uelbosque.tiendavirtual;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendavirtual.dao.CustomerDAO;
import com.tiendavirtual.dao.UserDAO;
import com.tiendavirtual.dto.CustomerDTO;
import com.tiendavirtual.dto.UserDTO;

@RestController
public class TiendaVirtualControlador {
	
	@RequestMapping("/crearUsuario")
	public String InsertarCliente(UserDTO nUser) {
		UserDAO uDao = new UserDAO();
		uDao.createUser(nUser);
		return "Microservicio de insersiÃ³n de usuarios";
	}
	
	
	@RequestMapping("/buscarUsuario")
	public String BuscarUsuario(String cedula) {
		UserDAO uDao = new UserDAO();
		uDao.searchUser(cedula);
		return "Microservicio buscar de usuarios";
	}
	
	@RequestMapping("/actualizarUsuario")
	public String ActualizarUsuario(UserDTO User) {
		UserDAO uDao = new UserDAO();
		uDao.updateUser(User);
		return "Microservicio actualizar de usuarios";
	}
	
	@RequestMapping("/eliminarUsuario")
	public String EliminarUsuario(String cedula) {
		UserDAO uDao = new UserDAO();
		uDao.delUser(cedula);
		return "Microservicio eliminiar de usuarios";
	}
	

	
	
	@RequestMapping("/buscarCliente")
	public String ConsultarCliente(String cedula) {
		CustomerDAO cDao=new CustomerDAO();
		cDao.searchCustomer(cedula);
		return "Microservicio de consulta de clientes";
	}
	
	@RequestMapping("/actualizarCliente")
	public String ActualizarCliente(CustomerDTO cliente) {
		CustomerDAO CDao=new CustomerDAO();
		CDao.updateCustomer(cliente);
		return "Microservicio de actualización de clientes";
	}
	
	@RequestMapping("/eliminarCliente")
	public String EliminarCliente() {
		
		return "Microservicio de eliminar  clientes";
	}
	
}