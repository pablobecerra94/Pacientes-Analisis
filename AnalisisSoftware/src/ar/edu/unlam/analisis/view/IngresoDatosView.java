package ar.edu.unlam.analisis.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class IngresoDatosView extends JFrame{
	public IngresoDatosView() {
		getContentPane().setLayout(null);
		
		JLabel lblIngresoDePacientes = new JLabel("Ingreso de Pacientes");
		lblIngresoDePacientes.setBounds(158, 18, 135, 16);
		getContentPane().add(lblIngresoDePacientes);
		
		JButton btnDatosPaciente = new JButton("Datos Paciente");
		btnDatosPaciente.setBounds(158, 73, 152, 29);
		getContentPane().add(btnDatosPaciente);
		
		JButton btnSituacionPaciente = new JButton("Situacion Paciente");
		btnSituacionPaciente.setBounds(158, 114, 152, 29);
		getContentPane().add(btnSituacionPaciente);
		
		JButton btnDatosMedico = new JButton("Datos Medico");
		btnDatosMedico.setBounds(158, 155, 152, 29);
		getContentPane().add(btnDatosMedico);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(327, 243, 117, 29);
		getContentPane().add(btnVolver);
	}

}
