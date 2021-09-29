package com.uelbosque.tiendavirtual;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendavirtual.dao.CustomerDAO;
import com.tiendavirtual.dao.SuppliersDAO;
import com.tiendavirtual.dao.UserDAO;
import com.tiendavirtual.dto.CustomerDTO;
import com.tiendavirtual.dto.SuppliersDTO;
import com.tiendavirtual.dto.UserDTO;

@RestController
public class TiendaVirtualControlador {
	
	@RequestMapping("/crearUsuario")
	public String InsertarUsuario(UserDTO nUser) {
		UserDAO uDao = new UserDAO();
		uDao.createUser(nUser);
		return "Microservicio de insersión de usuarios";
	}
	
	@RequestMapping("/buscarUsuario")
	public UserDTO BuscarUsuario(String cedula) {
		UserDAO uDao = new UserDAO();
		return uDao.searchUser(cedula);
	}
	
	@RequestMapping("/actualizarUsuario")
	public String ActualizarUsuario(UserDTO User) {
		UserDAO uDao = new UserDAO();
		uDao.updateUser(User);
		return "Microservicio actualizar de usuarios";
	}
	
	@RequestMapping("/eliminarUsuario")
	public String EliminarUsuario(String cedula) {
		System.out.println(cedula);
		UserDAO uDao = new UserDAO();
		uDao.delUser(cedula);
		return "Microservicio eliminiar de usuarios";
	}
	
	// Clientes
	
	@RequestMapping("/crearCliente")
	public String InsertarCliente(CustomerDTO customer) {
		CustomerDAO cDao=new CustomerDAO();
		cDao.createCustomer(customer);
		return "Microservicio de insersión de usuarios";
	}
	
	@RequestMapping("/buscarCliente")
	public CustomerDTO ConsultarCliente(String cedula) {
		CustomerDAO cDao=new CustomerDAO();
		return cDao.searchCustomer(cedula);
	}
	
	@RequestMapping("/actualizarCliente")
	public String ActualizarCliente(CustomerDTO cliente) {
		CustomerDAO CDao=new CustomerDAO();
		CDao.updateCustomer(cliente);
		return "Microservicio de actualizaci�n de clientes";
	}
	
	@RequestMapping("/eliminarCliente")
	public String EliminarCliente(String cedula) {
		CustomerDAO CDao=new CustomerDAO();
		CDao.delCustomer(cedula);
		return "Microservicio de eliminar  clientes";
	}
	// provedores

		@RequestMapping("/buscarProveedor")
		public SuppliersDTO ConsultarProvedor(String nit) {
			SuppliersDAO pDao = new SuppliersDAO();
			return pDao.searchSupplier(nit);
		}

		@RequestMapping("/crearProveedor")
		public String InsertarProvedor(SuppliersDTO supplier) {
			SuppliersDAO pDao = new SuppliersDAO();
			pDao.createSupplier(supplier);
			return "Microservicio de insersi�nn de Provedores";
		}

		@RequestMapping("/actualizarProveedor")
		public String ActualizarProvedor(SuppliersDTO supplier) {
			SuppliersDAO pDao = new SuppliersDAO();
			pDao.updateSupplier(supplier);
			return "Microservicio de actualizaci�n de Provedores";
		}

		@RequestMapping("/eliminarProveedor")
		public String EliminarProvedores(String nit) {
			SuppliersDAO pDao = new SuppliersDAO();
			pDao.delSupplier(nit);
			return "Microservicio de eliminar  Provedores";
		}

	
	
	
	
}