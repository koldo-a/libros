package com.ipartek.formacion.libros.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ipartek.formacion.libros.dtos.LibroDTO;
import com.ipartek.formacion.libros.dtos.AutorDTO;

public class LibroAccesoDatos {
	private static final Logger LOG = Logger.getLogger(LibroAccesoDatos.class.getName());
	
	private static final String SQL_SELECT = "SELECT * FROM autores a JOIN libros l ON l.autores_id = a.id";
	private static final String SQL_SELECT_TITULO = SQL_SELECT + " WHERE l.titulo LIKE ?";

	public static ArrayList<LibroDTO> obtenerTodos() {
		var libros = new ArrayList<LibroDTO>();

		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT);
				ResultSet rs = pst.executeQuery()) {
			
			LibroDTO libro;
			AutorDTO autor;

			while (rs.next()) {
				autor = new AutorDTO(rs.getLong("a.id"), rs.getString("a.nombre"), rs.getString("a.apellidos")
						);
				libro = new LibroDTO(rs.getLong("l.id"), rs.getString("l.isbn"), rs.getString("l.titulo"), autor);
				
				LOG.info("SE HA CREADO EL LIBRO: " + libro);
				
				libros.add(libro);
			}

			return libros;
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "NO SE HAN PODIDO OBTENER LOS LIBROS CON SUS AUTORES", e);
			throw new RuntimeException("Error en la select", e);
		}
	}
	
	public static ArrayList<LibroDTO> buscarPorTitulo(String titulo) {
		var libros = new ArrayList<LibroDTO>();

		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_TITULO);
				) {
			pst.setString(1, "%" + titulo + "%");
			
			ResultSet rs = pst.executeQuery();
			
			LibroDTO libro;
			AutorDTO autor;

			while (rs.next()) {
				autor = new AutorDTO(rs.getLong("a.id"), rs.getString("a.nombre"), rs.getString("a.apellidos")
						);
				libro = new LibroDTO(rs.getLong("l.id"), rs.getString("l.isbn"), rs.getString("l.titulo"), autor);
				
				LOG.info("SE HA CREADO EL LIBRO: " + libro);
				
				libros.add(libro);
			}

			return libros;
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "NO SE HAN PODIDO OBTENER LOS LIBROS CON SUS AUTORES", e);
			throw new RuntimeException("Error en la select", e);
		}
	}
}
