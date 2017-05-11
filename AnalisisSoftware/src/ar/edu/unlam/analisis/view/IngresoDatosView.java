package ar.edu.unlam.analisis.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class IngresoDatosView extends JFrame{
	public IngresoDatosView() {
		getContentPane().setLayout(null);
		setSize(new Dimension(500, 500));
		setLocationRelativeTo(null);
		JLabel lblIngresoDePacientes = new JLabel("Ingreso de Pacientes");
		lblIngresoDePacientes.setBounds(158, 18, 135, 16);
		getContentPane().add(lblIngresoDePacientes);
		
		JButton btnDatosPaciente = new JButton("Datos Paciente");
		btnDatosPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatosPacienteView dp = new DatosPacienteView();
				dp.setVisible(true);
				dispose();
			}
		});
		btnDatosPaciente.setBounds(158, 73, 152, 29);
		getContentPane().add(btnDatosPaciente);
		
		JButton btnSituacionPaciente = new JButton("Situacion Paciente");
		btnSituacionPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SituacionPacienteView spw = new SituacionPacienteView();
				spw.setVisible(true);
				dispose();
			}
		});
		btnSituacionPaciente.setBounds(158, 114, 152, 29);
		getContentPane().add(btnSituacionPaciente);
		
		JButton btnDatosMedico = new JButton("Datos Medico");
		btnDatosMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatosMedicoView dmw = new DatosMedicoView();
				dmw.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnDatosMedico.setBounds(158, 155, 152, 29);
		getContentPane().add(btnDatosMedico);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView mw = new MainView();
				mw.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(327, 243, 117, 29);
		getContentPane().add(btnVolver);
	}

}
