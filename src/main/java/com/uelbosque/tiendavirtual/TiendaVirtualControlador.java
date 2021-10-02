package com.uelbosque.tiendavirtual;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendavirtual.dao.CustomerDAO;
import com.tiendavirtual.dao.LoginDAO;
import com.tiendavirtual.dao.ProductsDAO;
import com.tiendavirtual.dao.SalesDetailsDAO;
import com.tiendavirtual.dao.SuppliersDAO;
import com.tiendavirtual.dao.UserDAO;
import com.tiendavirtual.dto.CustomerDTO;
import com.tiendavirtual.dto.ProductsDTO;
import com.tiendavirtual.dto.SalesDetailsDTO;
import com.tiendavirtual.dto.SuppliersDTO;
import com.tiendavirtual.dto.UserDTO;

@RestController
public class TiendaVirtualControlador {
	//Login
	
	@RequestMapping("/loginUser")
	public boolean Login(String userNick, String userPass) {
		LoginDAO lDao = new LoginDAO();
		return lDao.login(userNick, userPass);
	}
	
	//Usuarios

	@RequestMapping("/crearUsuario")
	public String InsertarUsuario(UserDTO nUser) {
		UserDAO uDao = new UserDAO();
		uDao.createUser(nUser);
		return "Microservicio de insersión de usuarios";
	}

	@RequestMapping("/buscarUsuario")
	public ArrayList<UserDTO> BuscarUsuario(String cedula) {
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
		CustomerDAO cDao = new CustomerDAO();
		cDao.createCustomer(customer);
		return "Microservicio de insersión de usuarios";
	}

	@RequestMapping("/buscarCliente")
	public ArrayList<CustomerDTO> ConsultarCliente(String cedula) {
		CustomerDAO cDao = new CustomerDAO();
		return cDao.searchCustomer(cedula);
	}

	@RequestMapping("/actualizarCliente")
	public String ActualizarCliente(CustomerDTO cliente) {
		CustomerDAO CDao = new CustomerDAO();
		CDao.updateCustomer(cliente);
		return "Microservicio de actualizaci�n de clientes";
	}

	@RequestMapping("/eliminarCliente")
	public String EliminarCliente(String cedula) {
		CustomerDAO CDao = new CustomerDAO();
		CDao.delCustomer(cedula);
		return "Microservicio de eliminar  clientes";
	}
	// Proveedores

	@RequestMapping("/buscarProveedor")
	public ArrayList<SuppliersDTO> ConsultarProvedor(String nit) {
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

	// Productos

	@RequestMapping("/crearProductos")
	public String InsertarProductos(ProductsDTO prod) {
		ProductsDAO prDao = new ProductsDAO();
		prDao.createProducts(prod);
		return "Microservicio de insersi�nn de Productos";
	}

	@RequestMapping("/buscarPrudcts")
	public ProductsDTO ConsultarPrductos(String name) {
		ProductsDAO prDao = new ProductsDAO();
		return prDao.searchProducts(name);
	}

	@RequestMapping("/actualizarProductos")
	public String ActualizarProductos(ProductsDTO prod) {
		ProductsDAO prDao = new ProductsDAO();
		prDao.updateProducts(prod);
		return "Microservicio de actualizaci�n de Productos";
	}

	@RequestMapping("/eliminarProductos")
	public String EliminarProductos(String cedula) {
		ProductsDAO prDao = new ProductsDAO();
		prDao.delUser(cedula);
		return "Microservicio de eliminar  Productos";
	}
	
	//Detalles Ventas
	
	
	@RequestMapping("/crearDetVentas")
	public String InsertSalesDetails(SalesDetailsDTO det) {
		SalesDetailsDAO svDao = new SalesDetailsDAO();
		svDao.createSalesDetails(det);
		return "Microservicio de insersi�nn de Detalles de Ventas";
	}

	@RequestMapping("/buscarDetVentas")
	public SalesDetailsDTO SearchSalesDetails(String cedula) {
		SalesDetailsDAO svDao = new SalesDetailsDAO();
		return svDao.searchSalesDetails(cedula);
	}

	@RequestMapping("/actualizarDetVentas")
	public String UpdateSalesDetails(SalesDetailsDAO customer) {
		SalesDetailsDAO svDao = new SalesDetailsDAO();
		svDao.updateSalesDetails(customer);
		return "Microservicio de actualizaci�n de Detalles de Ventas";
	}

	@RequestMapping("/eliminarDetVentas")
	public String DelSalesDetails(String cedula) {
		SalesDetailsDAO prDao = new SalesDetailsDAO();
		prDao.delSalesDetails(cedula);
		return "Microservicio de eliminar  Detalles de Ventas";
	}
	

}