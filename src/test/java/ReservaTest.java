import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import Conectar.ConnectionFactory;

public class ReservaTest {

	public static void main(String[] args) throws SQLException {
		final Connection con = new ConnectionFactory().recuperaConexion();
		
		try (con) {
			final PreparedStatement statement = con.prepareStatement("INSERT INTO RESERVA "
					+ "(FECHA_ENTRADA,FECHA_SALIDA,VALOR,FORMA_PAGO) "
					+ "VALUES (?,?,?,?)");
			try (statement){
				statement.setDate(1, new Date(2022, 8, 19));
				statement.setDate(2, new Date(2022, 7, 23));
				statement.setInt(3, 1000);
				statement.setString(4, "Efectivo");
				statement.execute();
				System.out.println("Se agrego correctamente!!!!!!!!!!!");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
	}
}




