package com.ipartek.formacion.libros.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.libros.dtos.AutorDTO;

public class AutorAccesoDatos {
	private static final String SQL_SELECT = "SELECT id, nombre, apellidos FROM autores";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO autores (nombre, apellidos) VALUES (?, ? ,?)";
	private static final String SQL_UPDATE = "UPDATE autores SET nombre=?, apellidos=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM autores WHERE id=?";

	public static ArrayList<AutorDTO> obtenerTodos() {
		var autores = new ArrayList<AutorDTO>();

		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT);
				ResultSet rs = pst.executeQuery()) {
			AutorDTO autor;

			while (rs.next()) {
				autor = new AutorDTO(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos")
						);

				autores.add(autor);
			}

			return autores;
		} catch (SQLException e) {
			throw new RuntimeException("Error en la select", e);
		}
	}

	public static AutorDTO obtenerPorId(Long id) {
		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID);) {
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return new AutorDTO(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error en la select", e);
		}
	}

	public static AutorDTO insertar(AutorDTO autor) {
		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT);) {
			pst.setString(1, autor.nombre());
			pst.setString(2, autor.apellidos());

			pst.executeUpdate();

			return autor;
		} catch (SQLException e) {
			throw new RuntimeException("Error en la insert", e);
		}
	}

	public static AutorDTO modificar(Long id, AutorDTO autor) {
		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE);) {
			pst.setString(1, autor.nombre());
			pst.setString(2, autor.apellidos());
			pst.setLong(4, id);

			pst.executeUpdate();

			return autor;
		} catch (SQLException e) {
			throw new RuntimeException("Error en la update", e);
		}
	}

	public static void borrar(Long id) {
		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE);) {
			pst.setLong(1, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error en la delete", e);
		}
	}
}
