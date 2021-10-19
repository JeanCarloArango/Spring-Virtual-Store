package com.uelbosque.tiendavirtual;

import java.io.File;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tiendavirtual.dao.*;
import com.tiendavirtual.dto.*;

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
	public Boolean EliminarUsuario(String cedula) {
		UserDAO uDao = new UserDAO();
		return uDao.delUser(cedula);
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

	@PostMapping("/cargarArchivo")
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		try {
			File fl = new File(fileName);
			file.transferTo(fl);
			ProductsDAO prDao = new ProductsDAO();
			prDao.fileUpload(fl);
			fl.delete();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok("Archivo cargado con exito.");
	}

	@RequestMapping("/buscarProductos")
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
	
	//Crear ventas
	
	@RequestMapping("/crearVenta")
	public String InsertarProvedor(SalesDTO sales) {
		SalesDAO pDao = new SalesDAO();
		pDao.createSales(sales);
		return "Microservicio de insersi�nn de Ventas";
	}
	
	//Detalles Ventas
	
	@RequestMapping("/buscarDetVentas")
	public ArrayList<SalesDetailsDTO> SearchSalesDetails() {
		SalesDetailsDAO svDao = new SalesDetailsDAO();
		return svDao.searchSalesDetails();
	}

	

}