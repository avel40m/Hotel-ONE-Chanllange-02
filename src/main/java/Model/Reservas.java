package Model;

import java.sql.Date;

public class Reservas {
	
	private int id;
	private Date fecha_entrada;
	private Date fecha_salida;
	private int valor;
	private String forma_pago;
	
	public Reservas(int id,Date fecha_entrada,Date fecha_salida,int valor, String forma_pago) {
		this.id = id;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor = valor;
		this.forma_pago = forma_pago;
	}
	
	public int getId() {
		return id;
	}
	public Date getFecha_entrada() {
		return fecha_entrada;
	}
	public Date getFecha_salida() {
		return fecha_salida;
	}
	public String getForma_pago() {
		return forma_pago;
	}
	public int getValor() {
		return valor;
	}
	
	@Override
	public String toString() {
		return String.format("{Fecha Entrada: %s , fecha salida: %s, valor: %s, forma pago: %s}",
							this.fecha_entrada,this.fecha_salida,this.valor,this.forma_pago);
	}
}
