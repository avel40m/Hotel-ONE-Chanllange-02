package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ReservaController;
import DAO.HuespedDAO;
import DAO.ReservaDAO;
import Model.Huesped;
import Model.Reservas;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	private HuespedDAO huespedDAO = new HuespedDAO();
	private ReservaController reservaController = new ReservaController();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(647, 85, 158, 31);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buscar = txtBuscar.getText();
				
				if (tbHuespedes.isVisible()) {
					buscar(buscar);
					limpiarTablaHuesped();
					buscar(buscar);
				}
				if (tbReservas.isVisible()) {
					buscarReserva(buscar);
					limpiarTablaResevas();
					buscarReserva(buscar);
				}
			}
		});
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/lupa2.png")));
		btnBuscar.setBounds(815, 75, 54, 41);
		contentPane.add(btnBuscar);
		
		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/editar-texto.png")));
		btnEditar.setBackground(SystemColor.menu);
		btnEditar.setBounds(587, 416, 54, 41);
		contentPane.add(btnEditar);
		
		JLabel lblNewLabel_4 = new JLabel("Sistema de Búsqueda");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_4.setBounds(155, 42, 258, 42);
		contentPane.add(lblNewLabel_4);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(815, 416, 54, 41);
		contentPane.add(btnSalir);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBounds(10, 127, 874, 265);
		contentPane.add(panel);
		
		tbHuespedes = new JTable();
		tbHuespedes.setFont(new Font("Arial", Font.PLAIN, 14));
		model1 = (DefaultTableModel) tbHuespedes.getModel();
		
		model1.addColumn("ID");
		model1.addColumn("NOMBRE");
		model1.addColumn("APELLIDO");
		model1.addColumn("FECHA NACIMIENTO");
		model1.addColumn("NACIONALIDAD");
		model1.addColumn("TELEFONO"); 
		listarHuespedes();
		
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), tbHuespedes, null);
		
		tbReservas = new JTable();
		tbReservas.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/calendario.png")), tbReservas, null);
		model2 = (DefaultTableModel) tbReservas.getModel();
		model2.addColumn("ID");
		model2.addColumn("FECHA_ENTRADA");
		model2.addColumn("FECHA_SALIDA");
		model2.addColumn("VALOR");
		model2.addColumn("FORMA PAGO");
		
		listarReservas();

		JButton btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/deletar.png")));
		btnEliminar.setBackground(SystemColor.menu);
		btnEliminar.setBounds(651, 416, 54, 41);
		contentPane.add(btnEliminar);
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tbHuespedes.isVisible()) {
					eliminarHuespedes();
					limpiarTablaHuesped();
					listarHuespedes();
				} else if (tbReservas.isVisible()) {
					eliminarReserva();
					limpiarTablaResevas();
					listarReservas();
				}
			}
			
		});
		
		JButton btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(713, 416, 54, 41);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(25, 10, 104, 107);
		contentPane.add(lblNewLabel_2);
		setResizable(false);
		
	}
	
	public void listarHuespedes() {
		List<Huesped> listadoHuespedes = huespedDAO.listarHuesped();
		listadoHuespedes.forEach(huesped -> model1.addRow(new Object[] {
				huesped.getId(),
				huesped.getNombre(),
				huesped.getApellido(),
				huesped.getFecha_nacimiento(),
				huesped.getNacionalida(),
				huesped.getTelefono()
		}));
	}
	
	public void buscar(String buscar) {
		List<Huesped> listadoHuespedes = huespedDAO.buscar(buscar);
		listadoHuespedes.forEach(huesped -> model1.addRow(new Object[] {
				huesped.getId(),
				huesped.getNombre(),
				huesped.getApellido(),
				huesped.getFecha_nacimiento(),
				huesped.getNacionalida(),
				huesped.getTelefono()
		}));
	}
	
	public void listarReservas() {
		List<Reservas> listaReserva = reservaController.listarReservas();
		listaReserva.forEach(reserva -> model2.addRow(new Object[] {
				reserva.getId(),
				reserva.getFecha_entrada(),
				reserva.getFecha_salida(),
				reserva.getValor(),
				reserva.getForma_pago()
		}));
	}
	
	public void buscarReserva(String buscar) {
		List<Reservas> listadoReserva = reservaController.buscarPorHuesped(buscar); 
		listadoReserva.forEach(reserva -> model2.addRow(new Object[] {
				reserva.getId(),
				reserva.getFecha_entrada(),
				reserva.getFecha_salida(),
				reserva.getValor(),
				reserva.getForma_pago()
		}));
	}
	
	public void limpiarTablaHuesped() {
		model1.getDataVector().removeAllElements();
	}
	
	public void limpiarTablaResevas() {
		model2.getDataVector().removeAllElements();
	}
	
	private boolean tieneFilaElegida() {
		return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedRowCount() == 0;
	}
	
	private boolean tieneFilaHuesped() {
		return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedRowCount() == 0;
	}
	
	private void eliminarReserva() {
		if (tieneFilaElegida()) {
			JOptionPane.showMessageDialog(null, "Elija un item");
			return;
		}
		Optional.ofNullable(model2.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
			.ifPresentOrElse(fila -> {
				Integer id = Integer.valueOf(model2.getValueAt(tbReservas.getSelectedRow(), 0).toString());
				reservaController.eliminarReserva(id);
				JOptionPane.showMessageDialog(this,String.format("El item %d se elimino correctamente", id));
			}, () -> JOptionPane.showMessageDialog(this, "Elija el item"));	
	}
	
	private void eliminarHuespedes() {
		if (tieneFilaHuesped()) {
			JOptionPane.showMessageDialog(null, "Elija un item");
			return;
		}
		Optional.ofNullable(model1.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
			.ifPresentOrElse(fila -> {
				Integer id = Integer.valueOf(model1.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
				huespedDAO.eliminarHuesped(id);
				JOptionPane.showMessageDialog(this, String.format("El item %d se elimino correctamente",id));
			}, () -> JOptionPane.showMessageDialog(this, "Elija el item"));
	}
	
}
