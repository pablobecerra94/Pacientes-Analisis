package ar.edu.unlam.analisis.view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ar.edu.unlam.analisis.login.UserProvider;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class InformesView extends JFrame{
	public InformesView() {
		getContentPane().setLayout(null);
		setSize(new Dimension(500, 500));
		setLocationRelativeTo(null);
		JLabel lblControlDePacientes = new JLabel("Control de Pacientes");
		lblControlDePacientes.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblControlDePacientes.setBounds(132, 17, 185, 16);
		getContentPane().add(lblControlDePacientes);
		
		JLabel lblInformes = new JLabel("Informes");
		lblInformes.setBounds(186, 45, 61, 16);
		getContentPane().add(lblInformes);
		
		JButton btnListadoDePacientes = new JButton("Listado de Pacientes Por Medico");
		btnListadoDePacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PacientesPorMedicoView ppmw = new PacientesPorMedicoView();
				ppmw.setVisible(true);
				dispose();
			}
		});
		btnListadoDePacientes.setBounds(71, 86, 300, 29);
		getContentPane().add(btnListadoDePacientes);
		
		JButton btnEnfrermedadesQueAtiende = new JButton("Enfrermedades que atiende cada Medico");
		btnEnfrermedadesQueAtiende.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnfermedadesMedicoView emw = new EnfermedadesMedicoView();
				emw.setVisible(true);
				dispose();
			}
		});
		btnEnfrermedadesQueAtiende.setBounds(71, 127, 300, 29);
		getContentPane().add(btnEnfrermedadesQueAtiende);
		
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
		
		JLabel label = new JLabel("Usuario: "+UserProvider.getUsuarioLogueado());
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		label.setBounds(6, 17, 90 + label.getText().length(), 16);
		getContentPane().add(label);
	}

}
