package Controller;

import java.sql.Date;
import java.util.List;

import DAO.ReservaDAO;
import Model.Reservas;

public class ReservaController {
	
	public void guardarReserva(Date fechaE, Date fechaS, int valor, String pago) {
		ReservaDAO.guardarReserva(fechaE,fechaS,valor,pago);
	}
	
	public int obtenerUltimaReserva() {
		return ReservaDAO.obtenerReserva();
	}

	public List<Reservas> buscarPorHuesped(String buscar) {
		return ReservaDAO.buscarPorHuesped(buscar);
	}

	public List<Reservas> listarReservas() {
		return ReservaDAO.listarReservas();
	}

	public static void eliminarReserva(Integer id) {
		ReservaDAO.eliminarReserva(id);
		
	}

	public void modificarReserva(Reservas reservas) {
		ReservaDAO.editarReserva(reservas);
		
	}
}
