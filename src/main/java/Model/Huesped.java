package Model;

import java.sql.Date;

public class Huesped {
	private int id;
	private String nombre;
	private String apellido;
	private Date fecha_nacimiento;
	private String nacionalida;
	private String telefono;
	
	public Huesped(int id,String nombre,String apellido,Date fecha_nacimiento,String nacionalidad,String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.nacionalida = nacionalidad;
		this.telefono = telefono;
	}
	
	public String getApellido() {
		return apellido;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public int getId() {
		return id;
	}
	public String getNacionalida() {
		return nacionalida;
	}
	public String getNombre() {
		return nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	
	
}
