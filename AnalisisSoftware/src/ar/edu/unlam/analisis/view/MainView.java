package ar.edu.unlam.analisis.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * Vista Principal del sistema
 * @author fpezzola
 *
 */

@SuppressWarnings("serial")
public class MainView extends JFrame {
	public MainView() {
		getContentPane().setLayout(null);
		setSize(new Dimension(500, 500));
		setLocationRelativeTo(null);
		JLabel lblTitle = new JLabel("Centro Medico Los Laureles");
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTitle.setBounds(132, 19, 312, 16);
		getContentPane().add(lblTitle);
		
		JLabel lblControlDePacientes = new JLabel("Control de Pacientes");
		lblControlDePacientes.setBounds(175, 47, 167, 16);
		getContentPane().add(lblControlDePacientes);
		
		JButton btnIngresoDatos = new JButton("Ingreso de Datos");
		btnIngresoDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresoDatosView idw = new IngresoDatosView();
				idw.setVisible(true);
				dispose();
				
			}
		});
		btnIngresoDatos.setBounds(166, 102, 150, 29);
		getContentPane().add(btnIngresoDatos);
		
		JButton btnInformes = new JButton("Informes");
		btnInformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformesView iw = new InformesView();
				iw.setVisible(true);
				dispose();
			}
		});
		btnInformes.setBounds(166, 159, 150, 29);
		getContentPane().add(btnInformes);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView lw = new LoginView();
				lw.setVisible(true);
				dispose();
			}
		});
		btnCerrarSesion.setBounds(166, 257, 150, 29);
		getContentPane().add(btnCerrarSesion);
	}
}
