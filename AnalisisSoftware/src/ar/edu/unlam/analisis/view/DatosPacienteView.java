package ar.edu.unlam.analisis.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class DatosPacienteView extends JFrame {
	private JTextField textFieldCodigo;
	private JTextField textFieldNombre;
	public DatosPacienteView() {
		getContentPane().setLayout(null);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(264, 132, 117, 29);
		getContentPane().add(btnRegistrar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(86, 132, 117, 29);
		getContentPane().add(btnLimpiar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(327, 243, 117, 29);
		getContentPane().add(btnVolver);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(159, 45, 178, 26);
		getContentPane().add(textFieldCodigo);
		textFieldCodigo.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(159, 79, 178, 26);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(86, 50, 61, 16);
		getContentPane().add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(86, 84, 61, 16);
		getContentPane().add(lblNombre);
		
		JLabel lblNuevoPaciente = new JLabel("Nuevo Paciente");
		lblNuevoPaciente.setBounds(183, 6, 103, 16);
		getContentPane().add(lblNuevoPaciente);
	}
}
