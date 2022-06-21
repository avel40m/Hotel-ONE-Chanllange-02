import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conectar.ConnectionFactory;
import Model.Reservas;

public class ObtenerUltimaReservaTest {
	
	public static void main(String[] args) throws SQLException {
	
	final Connection con = new ConnectionFactory().recuperaConexion();
	List<Reservas> reservas = new ArrayList<Reservas>();
	
		try (con) {
			final PreparedStatement statement = con.prepareStatement(""
					+ "SELECT ID,FECHA_ENTRADA,FECHA_SALIDA,VALOR,FORMA_PAGO FROM RESERVA");
			try (statement){
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					Reservas reserva = new Reservas(resultSet.getInt("ID"),
													resultSet.getDate("FECHA_ENTRADA"),
													resultSet.getDate("FECHA_SALIDA"),
													resultSet.getInt("VALOR"),
													resultSet.getString("FORMA_PAGO"));
					reservas.add(reserva);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
		
		reservas.forEach( reserva -> System.out.println(reserva));
		System.out.println(reservas.size());
		
	}

	}

