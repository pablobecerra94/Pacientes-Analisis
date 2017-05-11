package ar.edu.unlam.analisis.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import ar.edu.unlam.analisis.pacientes.controlpac;
import ar.edu.unlam.analisis.util.HandleResponseUtil;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DatosPacienteView extends JFrame {
	private JTextField textFieldCodigo;
	private JTextField textFieldNombre;
	public DatosPacienteView() {
		getContentPane().setLayout(null);
		setSize(new Dimension(500, 500));
		setLocationRelativeTo(null);
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder errors = new StringBuilder("");
				if(textFieldCodigo.getText().isEmpty()){
					errors.append("*El codigo es necesario").append("\n");
				}
				if(textFieldNombre.getText().isEmpty()){
					errors.append("*El nombre es necesario").append("\n");
				}
				
				if(errors.toString().isEmpty()){
					try {
						controlpac.nuevoPaciente(textFieldCodigo.getText(), textFieldNombre.getText());
						limpiar();
						HandleResponseUtil.showMessageSuccess(HandleResponseUtil.COMMON_SUCCESS);
					} catch (FileNotFoundException e1) {
						HandleResponseUtil.showMessageError(HandleResponseUtil.COMMON_ERROR);
					}
				}else{
					HandleResponseUtil.showMessageError(errors.toString());
				}
			}
		});
		btnRegistrar.setBounds(264, 132, 117, 29);
		getContentPane().add(btnRegistrar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setBounds(86, 132, 117, 29);
		getContentPane().add(btnLimpiar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresoDatosView idw = new IngresoDatosView();
				idw.setVisible(true);
				dispose();
			}
		});
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
	
	public void limpiar(){
		textFieldCodigo.setText("");
		textFieldNombre.setText("");
	}
}
