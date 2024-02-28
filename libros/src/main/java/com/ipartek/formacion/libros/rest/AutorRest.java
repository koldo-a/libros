package com.ipartek.formacion.libros.rest;

import java.util.ArrayList;

import com.ipartek.formacion.libros.accesodatos.AutorAccesoDatos;
import com.ipartek.formacion.libros.dtos.AutorDTO;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/autores")
public class AutorRest {
	@GET
	public ArrayList<AutorDTO> obtenerTodos() {
		return AutorAccesoDatos.obtenerTodos();
	}
	
	@GET
	@Path("/{id}")
	public AutorDTO obtenerPorId(@PathParam("id") Long id) {
		return AutorAccesoDatos.obtenerPorId(id);
	}
	
	@POST
	public AutorDTO insertar(AutorDTO autor) {
		return AutorAccesoDatos.insertar(autor);
	}
	
	@PUT
	@Path("/{id}")
	public AutorDTO modificar(@PathParam("id") Long id, AutorDTO autor) {
		return AutorAccesoDatos.modificar(id, autor);
	}
	
	@DELETE
	@Path("/{id}")
	public void borrar(@PathParam("id") Long id) {
		AutorAccesoDatos.borrar(id);
	}
	
}
