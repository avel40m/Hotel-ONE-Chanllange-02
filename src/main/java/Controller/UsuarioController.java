package Controller;

import DAO.UsuarioDAO;

public class UsuarioController {

	public boolean Validacion(String correo, String password) {
		return UsuarioDAO.validacion(correo,password);
	}

}
