package com.ipartek.formacion.libros.rest;

import java.util.ArrayList;

import com.ipartek.formacion.libros.accesodatos.LibroAccesoDatos;
import com.ipartek.formacion.libros.dtos.LibroDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/libros")
public class LibroRest {
	@GET
	public ArrayList<LibroDTO> obtenerTodos() {
		return LibroAccesoDatos.obtenerTodos();
	}
	
	@GET
	@Path("/buscar")
	public ArrayList<LibroDTO> obtenerTodos(@QueryParam("titulo") String titulo) {
		return LibroAccesoDatos.buscarPorTitulo(titulo);
	}
	
}
