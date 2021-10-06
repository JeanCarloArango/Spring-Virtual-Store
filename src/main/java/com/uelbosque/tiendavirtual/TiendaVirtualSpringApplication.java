package com.uelbosque.tiendavirtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TiendaVirtualSpringApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/TiendaVirtualApp");
		SpringApplication.run(TiendaVirtualSpringApplication.class, args);
	}

}