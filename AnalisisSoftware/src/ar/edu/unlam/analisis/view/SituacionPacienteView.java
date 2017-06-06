package ar.edu.unlam.analisis.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ar.edu.unlam.analisis.login.UserProvider;
import ar.edu.unlam.analisis.pacientes.controlpac;
import ar.edu.unlam.analisis.util.HandleResponseUtil;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class SituacionPacienteView extends JFrame{
	private JTextField textFieldCodigoPaciente;
	private JTextField textFieldCodigoMedico;
	private JTextArea textAreaDiagnostico;
	public SituacionPacienteView() {
		setSize(new Dimension(500, 500));
		setLocationRelativeTo(null);
		JLabel lblSituacionDelPaciente = new JLabel("Situacion Del Paciente");
		lblSituacionDelPaciente.setBounds(142, 17, 185, 16);
		
		JLabel lblCodigoPaciente = new JLabel("Codigo Paciente:");
		lblCodigoPaciente.setBounds(32, 56, 105, 16);
		
		JLabel lblCodigoMedico = new JLabel("Codigo Medico:");
		lblCodigoMedico.setBounds(32, 98, 105, 16);
		
		JLabel lblDiagnostico = new JLabel("Diagnostico:");
		lblDiagnostico.setBounds(32, 147, 105, 16);
		
		textFieldCodigoPaciente = new JTextField();
		textFieldCodigoPaciente.setBounds(142, 51, 203, 26);
		textFieldCodigoPaciente.setColumns(10);
		
		textFieldCodigoMedico = new JTextField();
		textFieldCodigoMedico.setBounds(142, 93, 203, 26);
		textFieldCodigoMedico.setColumns(10);
		
		textAreaDiagnostico = new JTextArea();
		textAreaDiagnostico.setBounds(142, 147, 203, 72);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(72, 231, 117, 29);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(259, 231, 117, 29);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder errors = new StringBuilder("");
				
				if(textFieldCodigoMedico.getText().isEmpty()){
					errors.append("*El codigo del medico es necesario.").append("\n");
				}
				if(textFieldCodigoPaciente.getText().isEmpty()){
					errors.append("*El codigo del paciente es necesario.").append("\n");
				}
				
				if(textAreaDiagnostico.getText().isEmpty()){
					errors.append("*El diagnostico es necesario.").append("\n");
				}
				
				if(errors.toString().isEmpty()){
					try {
						controlpac.nuevaSituacionPaciente(textFieldCodigoPaciente.getText(), textFieldCodigoMedico.getText(), textAreaDiagnostico.getText());
						limpiar();
						HandleResponseUtil.showMessageSuccess(HandleResponseUtil.COMMON_SUCCESS);
					} catch (Exception e1) {
						HandleResponseUtil.showMessageError(HandleResponseUtil.COMMON_ERROR);
					}
				}else{
					HandleResponseUtil.showMessageError(errors.toString());
				}
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(384, 300, 117, 29);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresoDatosView idw = new IngresoDatosView();
				idw.setVisible(true);
				dispose();
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(lblSituacionDelPaciente);
		getContentPane().add(lblCodigoPaciente);
		getContentPane().add(lblCodigoMedico);
		getContentPane().add(lblDiagnostico);
		getContentPane().add(textFieldCodigoPaciente);
		getContentPane().add(textFieldCodigoMedico);
		getContentPane().add(textAreaDiagnostico);
		getContentPane().add(btnLimpiar);
		getContentPane().add(btnRegistrar);
		getContentPane().add(btnVolver);
		
		JLabel label = new JLabel("Usuario: "+UserProvider.getUsuarioLogueado());
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		label.setBounds(6, 17, 90 + label.getText().length(), 16);
		getContentPane().add(label);
	}
	
	public void limpiar(){
		textFieldCodigoMedico.setText("");
		textFieldCodigoPaciente.setText("");
		textAreaDiagnostico.setText("");
	}
}
