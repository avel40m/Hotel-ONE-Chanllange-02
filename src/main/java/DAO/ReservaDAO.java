package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import Conectar.ConnectionFactory;
import Model.Reservas;

public class ReservaDAO {

	public static void guardarReserva(Date fechaE, Date fechaS, int valor, String pago) {
		final Connection con = new ConnectionFactory().recuperaConexion();
		
		try (con){
			final PreparedStatement statement = con.prepareStatement("INSERT INTO RESERVA "
					+ "(FECHA_ENTRADA,FECHA_SALIDA,VALOR,FORMA_PAGO) "
					+ "VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			try (statement){
				statement.setDate(1, fechaE);
				statement.setDate(2, fechaS);
				statement.setInt(3, valor);
				statement.setString(4, pago);
				statement.execute();				
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	
	public static int obtenerReserva() {
	int id = 0;
		try {
			Connection con = new ConnectionFactory().recuperaConexion();
			PreparedStatement statement = con.prepareStatement(""
					+ "SELECT ID FROM RESERVA ORDER BY ID DESC LIMIT 1");
			ResultSet resultSet = statement.executeQuery();	
			while (resultSet.next()) {
				id  = resultSet.getInt("ID");
			}			
			con.close();	
			return id;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<Reservas> buscarPorHuesped(String buscar) {
		List<Reservas> listadoReserva = new ArrayList<>();
		try {
			Connection con = new ConnectionFactory().recuperaConexion();
			PreparedStatement statement = con.prepareStatement("SELECT"
					+ " r.ID,FECHA_ENTRADA, FECHA_SALIDA,VALOR,FORMA_PAGO "
					+ " FROM RESERVA r INNER JOIN HUESPEDES h ON r.ID = h.ID_RESERVA "
					+ "WHERE r.ID = "+buscar+"");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Reservas reservas = new Reservas(resultSet.getInt("ID"),
												resultSet.getDate("FECHA_ENTRADA"),
												resultSet.getDate("FECHA_SALIDA"),
												resultSet.getInt("VALOR"),
												resultSet.getString("FORMA_PAGO"));
				listadoReserva.add(reservas);
			}
			return listadoReserva;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<Reservas> listarReservas() {
		List<Reservas> listaReservas = new ArrayList<>();
		final Connection con = new ConnectionFactory().recuperaConexion();
		try (con) {
			PreparedStatement statement = con.prepareStatement("SELECT"
					+ " ID,FECHA_ENTRADA, FECHA_SALIDA,VALOR,FORMA_PAGO "
					+ " FROM RESERVA");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Reservas reservas = new Reservas(resultSet.getInt("ID"),
												resultSet.getDate("FECHA_ENTRADA"),
												resultSet.getDate("FECHA_SALIDA"),
												resultSet.getInt("VALOR"),
												resultSet.getString("FORMA_PAGO"));
				listaReservas.add(reservas);
			}
			return listaReservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void eliminarReserva(Integer id){
		try {
		final Connection con = new ConnectionFactory().recuperaConexion();
		PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVA WHERE ID = ?");
		statement.setInt(1, id);
		statement.executeUpdate();
		con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void editarReserva(Reservas reservas) {
		try {
			Connection con = new ConnectionFactory().recuperaConexion();
			PreparedStatement statement = con.prepareStatement("UPDATE RESERVA SET "
					+ "FECHA_ENTRADA = ?,FECHA_SALIDA = ?, VALOR = ?,FORMA_PAGO = ? "
					+ "WHERE ID = ?");
			statement.setDate(1, reservas.getFecha_entrada());
			statement.setDate(2, reservas.getFecha_salida());
			statement.setInt(3, reservas.getValor());
			statement.setString(4, reservas.getForma_pago());
			statement.setInt(5, reservas.getId());
			statement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
