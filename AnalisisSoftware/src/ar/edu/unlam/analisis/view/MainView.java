package ar.edu.unlam.analisis.view;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ar.edu.unlam.analisis.login.LoginService;
import ar.edu.unlam.analisis.login.UserProvider;
import ar.edu.unlam.analisis.util.HandleResponseUtil;

/**
 * Vista Principal del sistema
 * @author fpezzola
 *
 */

@SuppressWarnings("serial")
public class MainView extends JFrame {
	protected static final String RUTA_MANUAL_DE_USUARIO = "src//resources//manual.pdf";

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
		btnIngresoDatos.setToolTipText("Presione este botón para ingresasr nuevos datos al sistema");
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
		btnInformes.setToolTipText("Presione este botón para ver informes");
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
		btnCerrarSesion.setToolTipText("Presione este botón para salir y volver al menú de ingreso al sistema");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		btnCerrarSesion.setBounds(166, 257, 150, 29);
		getContentPane().add(btnCerrarSesion);
		
		JLabel label = new JLabel("Usuario: "+UserProvider.getUsuarioLogueado());
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		label.setBounds(6, 17, 90 + label.getText().length(), 16);
		getContentPane().add(label);
		
	
		JMenuBar menuBar = new JMenuBar();

		
		JMenu menu = new JMenu("Archivo");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("Menu para aspectos principales");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("Salir",KeyEvent.VK_S);
		menuItem.getAccessibleContext().setAccessibleDescription( "Presione para cerrar la sesion");
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}	
		});
		
		JMenu menuAyuda = new JMenu("Ayuda");
		menuAyuda.setMnemonic(KeyEvent.VK_H);
		menuAyuda.getAccessibleContext().setAccessibleDescription("Menu para obtener ayuda sobre el uso del sistema");
		menuBar.add(menuAyuda);
		
		JMenuItem manualItem = new JMenuItem("Manual de usuario",KeyEvent.VK_U);
		manualItem.getAccessibleContext().setAccessibleDescription( "Presione para abrir el manual de usuario");
		menuAyuda.add(manualItem);
		manualItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO Abrir pdf
				
			    try {
			    	File path = new File (RUTA_MANUAL_DE_USUARIO);
					Desktop.getDesktop().open(path);
				} catch (IOException e1) {
					HandleResponseUtil.showMessageError("Error al Manual de usuario");
				}
			}	
		});
		
		setJMenuBar(menuBar);
	}

	private void cerrarSesion() {
		LoginView lw = new LoginView();
		LoginService.logout();
		lw.setVisible(true);
		dispose();
	}
}
