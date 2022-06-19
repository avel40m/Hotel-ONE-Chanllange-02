package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conectar.ConnectionFactory;

public class UsuarioDAO {
	private Connection con;
	
	public UsuarioDAO(Connection con) {
		this.con = con;
	}

	public static boolean validacion(String correo, String password) {
		ConnectionFactory conectar = new ConnectionFactory();
		final Connection con = conectar.recuperaConexion();
		
		try (con){
			final PreparedStatement statement = con.prepareStatement("SELECT CORREO,PASSWORD FROM USUARIO");
			try (statement){
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				try (resultSet) {
					while (resultSet.next()) {
						if (correo.equals(resultSet.getString(1)) && password.equals(resultSet.getString(2))) {
							return true;
						}
					}
				}
			}
			return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
