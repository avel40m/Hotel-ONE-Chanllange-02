package Controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import Conectar.ConnectionFactory;
import Model.Huesped;

public class HuespedController {

	public static void guardarHuesped(String nombre, String apellido, Date nacimiento, String nacionalidad,
			String telefono, int id_reserva) throws SQLException{
		
		final Connection con = new ConnectionFactory().recuperaConexion();
		try (con) {
			final PreparedStatement statement = con.prepareStatement("INSERT INTO HUESPEDES "
					+ "(NOMBRE,APELLIDO,FECHA_NACIMIENTO,NACIONALIDAD,TELEFONO,ID_RESERVA) "
					+ "VALUES (?,?,?,?,?,?)");
			try (statement){
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, nacimiento);
				statement.setString(4, nacionalidad);
				statement.setString(5, telefono);
				statement.setInt(6, id_reserva);
				statement.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
		
	}

	public static List<Huesped> listaReserva(String buscar) {
		List<Huesped> huespedes = new ArrayList<Huesped>();
		final Connection con = new ConnectionFactory().recuperaConexion();
		
		try (con) {
			PreparedStatement statement = con.prepareStatement("SELECT "
					+ "ID,NOMBRE,APELLIDO,FECHA_NACIMIENTO,NACIONALIDAD,TELEFONO FROM HUESPEDES"
					+ " WHERE APELLIDO LIKE '%"+buscar+"%'");
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Huesped huesped = new Huesped(resultSet.getInt("ID"),
											resultSet.getString("NOMBRE"),
											resultSet.getString("APELLIDO"),
											resultSet.getDate("FECHA_NACIMIENTO"),
											resultSet.getString("NACIONALIDAD"),
											resultSet.getString("TELEFONO"));
				huespedes.add(huesped);
			}
			
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<Huesped> listarHuesped() {
		List<Huesped> listadoHuesped = new ArrayList<>();
		final Connection con = new ConnectionFactory().recuperaConexion();
		try (con) {
			PreparedStatement statement = con.prepareStatement("SELECT "
					+ "ID,NOMBRE,APELLIDO,FECHA_NACIMIENTO,NACIONALIDAD,TELEFONO FROM HUESPEDES");
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Huesped huesped = new Huesped(resultSet.getInt("ID"),
						resultSet.getString("NOMBRE"),
						resultSet.getString("APELLIDO"),
						resultSet.getDate("FECHA_NACIMIENTO"),
						resultSet.getString("NACIONALIDAD"),
						resultSet.getString("TELEFONO"));
				listadoHuesped.add(huesped);
			}
			
			return listadoHuesped;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void eliminarHuesped(Integer id) {
		try {
			final Connection con = new ConnectionFactory().recuperaConexion();
			PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");
			statement.setInt(1, id);
			statement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void modificarHuesped(Huesped huesped) {
		try {
			Connection con = new ConnectionFactory().recuperaConexion();
			PreparedStatement statement = con.prepareStatement("UPDATE HUESPEDES SET "
					+ "NOMBRE = ?,APELLIDO = ?,FECHA_NACIMIENTO = ?,NACIONALIDAD = ?,TELEFONO = ? "
					+ "WHERE ID = ?");
			statement.setString(1, huesped.getNombre());
			statement.setString(2, huesped.getApellido());
			statement.setDate(3, huesped.getFecha_nacimiento());
			statement.setString(4, huesped.getNacionalida());
			statement.setString(5, huesped.getTelefono());
			statement.setInt(6, huesped.getId());
			statement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
