package ar.edu.unlam.analisis.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class InformesView extends JFrame{
	public InformesView() {
		getContentPane().setLayout(null);
		
		JLabel lblControlDePacientes = new JLabel("Control de Pacientes");
		lblControlDePacientes.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblControlDePacientes.setBounds(132, 17, 185, 16);
		getContentPane().add(lblControlDePacientes);
		
		JLabel lblInformes = new JLabel("Informes");
		lblInformes.setBounds(186, 45, 61, 16);
		getContentPane().add(lblInformes);
		
		JButton btnListadoDePacientes = new JButton("Listado de Pacientes Por Medico");
		btnListadoDePacientes.setBounds(71, 86, 300, 29);
		getContentPane().add(btnListadoDePacientes);
		
		JButton btnEnfrermedadesQueAtiende = new JButton("Enfrermedades que atiende cada Medico");
		btnEnfrermedadesQueAtiende.setBounds(71, 127, 300, 29);
		getContentPane().add(btnEnfrermedadesQueAtiende);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(327, 243, 117, 29);
		getContentPane().add(btnVolver);
	}

}
