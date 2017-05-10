package ar.edu.unlam.analisis.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class DatosMedicoView extends JFrame{
	private JTextField textCodigo;
	private JTextField textNombre;
	private JTextField textEspecializacion;
	public DatosMedicoView() {
		getContentPane().setLayout(null);
		
		JLabel lblNuevoMedico = new JLabel("Nuevo Medico");
		lblNuevoMedico.setBounds(160, 6, 100, 16);
		getContentPane().add(lblNuevoMedico);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(140, 34, 130, 26);
		getContentPane().add(textCodigo);
		textCodigo.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(140, 65, 130, 26);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		textEspecializacion = new JTextField();
		textEspecializacion.setBounds(140, 103, 130, 26);
		getContentPane().add(textEspecializacion);
		textEspecializacion.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(37, 39, 61, 16);
		getContentPane().add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(37, 70, 61, 16);
		getContentPane().add(lblNombre);
		
		JLabel lblEspecializacion = new JLabel("Especializacion:");
		lblEspecializacion.setBounds(37, 108, 102, 16);
		getContentPane().add(lblEspecializacion);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(70, 154, 117, 29);
		getContentPane().add(btnLimpiar);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(217, 154, 117, 29);
		getContentPane().add(btnRegistrar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(327, 243, 117, 29);
		getContentPane().add(btnVolver);
	}

}
