package ar.edu.unlam.analisis.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Vista Principal del sistema
 * @author fpezzola
 *
 */

@SuppressWarnings("serial")
public class MainView extends JFrame {
	public MainView() {
		getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Centro Medico Los Laureles");
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTitle.setBounds(116, 19, 312, 16);
		getContentPane().add(lblTitle);
		
		JLabel lblControlDePacientes = new JLabel("Control de Pacientes");
		lblControlDePacientes.setBounds(166, 47, 167, 16);
		getContentPane().add(lblControlDePacientes);
		
		JButton btnIngresoDatos = new JButton("Ingreso de Datos");
		btnIngresoDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIngresoDatos.setBounds(166, 102, 150, 29);
		getContentPane().add(btnIngresoDatos);
		
		JButton btnInformes = new JButton("Informes");
		btnInformes.setBounds(166, 159, 150, 29);
		getContentPane().add(btnInformes);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(166, 257, 150, 29);
		getContentPane().add(btnSalir);
	}
}
