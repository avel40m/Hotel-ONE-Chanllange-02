package DAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import Controller.HuespedController;
import Model.Huesped;
import views.Reservas;

public class HuespedDAO {
	
	public void crearHuesped(String nombre,String apellido,Date nacimiento ,String nacionalidad,String telefono,int id_reserva) {
		try {
			HuespedController.guardarHuesped(nombre,apellido,nacimiento,nacionalidad,telefono,id_reserva);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		}

	public List<Huesped> buscar(String buscar) {
		return HuespedController.listaReserva(buscar);
	}

	public List<Huesped> listarHuesped() {
		return HuespedController.listarHuesped();
	}

	public static void eliminarHuesped(Integer id) {
		HuespedController.eliminarHuesped(id);
		
	}
}
