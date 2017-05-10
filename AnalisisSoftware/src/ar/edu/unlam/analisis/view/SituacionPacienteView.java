package ar.edu.unlam.analisis.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class SituacionPacienteView extends JFrame{
	private JTextField textFieldCodigoPaciente;
	private JTextField textFieldCodigoMedico;
	public SituacionPacienteView() {
		getContentPane().setLayout(null);
		
		JLabel lblSituacionDelPaciente = new JLabel("Situacion Del Paciente");
		lblSituacionDelPaciente.setBounds(142, 17, 185, 16);
		getContentPane().add(lblSituacionDelPaciente);
		
		JLabel lblCodigoPaciente = new JLabel("Codigo Paciente:");
		lblCodigoPaciente.setBounds(32, 56, 105, 16);
		getContentPane().add(lblCodigoPaciente);
		
		JLabel lblCodigoMedico = new JLabel("Codigo Medico:");
		lblCodigoMedico.setBounds(32, 98, 105, 16);
		getContentPane().add(lblCodigoMedico);
		
		JLabel lblDiagnostico = new JLabel("Diagnostico:");
		lblDiagnostico.setBounds(32, 147, 105, 16);
		getContentPane().add(lblDiagnostico);
		
		textFieldCodigoPaciente = new JTextField();
		textFieldCodigoPaciente.setBounds(142, 51, 203, 26);
		getContentPane().add(textFieldCodigoPaciente);
		textFieldCodigoPaciente.setColumns(10);
		
		textFieldCodigoMedico = new JTextField();
		textFieldCodigoMedico.setBounds(142, 93, 203, 26);
		getContentPane().add(textFieldCodigoMedico);
		textFieldCodigoMedico.setColumns(10);
		
		JTextArea textAreaDiagnostico = new JTextArea();
		textAreaDiagnostico.setBounds(142, 147, 203, 72);
		getContentPane().add(textAreaDiagnostico);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(72, 231, 117, 29);
		getContentPane().add(btnLimpiar);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(259, 231, 117, 29);
		getContentPane().add(btnRegistrar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(384, 300, 117, 29);
		getContentPane().add(btnVolver);
	}
}
