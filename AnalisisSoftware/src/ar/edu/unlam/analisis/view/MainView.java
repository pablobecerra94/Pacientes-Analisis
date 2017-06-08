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
		getContentPane().setLayout(null);//setea un layout absoluto
		setSize(new Dimension(500, 500));//setea la dimension de la ventana
		setLocationRelativeTo(null); //setea que no este relativa a nada la pantalla
		JLabel lblTitle = new JLabel("Centro Medico Los Laureles");//crea un label
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 16));//setea la fuente del label
		lblTitle.setBounds(132, 19, 312, 16);//setea las medidas del label
		getContentPane().add(lblTitle);//lo agrega a la ventana
		
		JLabel lblControlDePacientes = new JLabel("Control de Pacientes");//crea un label
		lblControlDePacientes.setBounds(175, 47, 167, 16);//setea las medidas
		getContentPane().add(lblControlDePacientes);
		
		JButton btnIngresoDatos = new JButton("Ingreso de Datos");//crea un boton
		btnIngresoDatos.setToolTipText("Presione este botón para ingresasr nuevos datos al sistema");//agrega el tooltip text
		btnIngresoDatos.addActionListener(new ActionListener() {//agrega un listener para escuchar el clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				IngresoDatosView idw = new IngresoDatosView(); //crea una pantalla de ingreso de datos
				idw.setVisible(true); //pone la pantalla de ingreso de datos visible
				dispose(); //cierra la pantalla
				
			}
		});
		btnIngresoDatos.setBounds(166, 102, 150, 29);//setea las medidas del boton
		getContentPane().add(btnIngresoDatos); //lo agrega a la pantalla
		
		JButton btnInformes = new JButton("Informes");//crea un boton
		btnInformes.setToolTipText("Presione este botón para ver informes");//agrega el tooltip text
		btnInformes.addActionListener(new ActionListener() { //agrega un listener para escuchar el clicks
			public void actionPerformed(ActionEvent e) { //metodo para escuchar clicks
				InformesView iw = new InformesView();// crea una pantalla de informes
				iw.setVisible(true);//pone la pantalla de informes visible
				dispose(); //cierra la pantalla
			}
		});
		btnInformes.setBounds(166, 159, 150, 29);//setea las medidas
		getContentPane().add(btnInformes); // lo agrega a la pantalla
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesion");//crea un boton
		btnCerrarSesion.setToolTipText("Presione este botón para salir y volver al menú de ingreso al sistema");//agrega el tooltip text
		btnCerrarSesion.addActionListener(new ActionListener() {//agrega un listener para escuchar el clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				cerrarSesion(); //cierra la sesion
			}
		});
		btnCerrarSesion.setBounds(166, 257, 150, 29); //setea las medidas del boton
		getContentPane().add(btnCerrarSesion); //lo agrega a la pantalla
		
		JLabel label = new JLabel("Usuario: "+UserProvider.getUsuarioLogueado());//crea un label
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 10)); //setea la fuente del label
		label.setBounds(6, 17, 90 + label.getText().length(), 16); //setea las medidas del label
		getContentPane().add(label);//lo agrega a la pantalla
		
	
		JMenuBar menuBar = new JMenuBar(); //crea un JMenuBar

		
		JMenu menu = new JMenu("Archivo"); //Crea un JMenu
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("Menu para aspectos principales");
		menuBar.add(menu); //lo agrega al menu

		JMenuItem menuItem = new JMenuItem("Salir",KeyEvent.VK_S);//Crea un JMenuItem
		menuItem.getAccessibleContext().setAccessibleDescription( "Presione para cerrar la sesion");
		menu.add(menuItem);//lo agrega al menu
		menuItem.addActionListener(new ActionListener(){//agrega un listener para escuchar los clicks
			@Override
			public void actionPerformed(ActionEvent e) { //metodo para escuchar clicks
				cerrarSesion();//Cierra la sesion
			}	
		});
		
		JMenu menuAyuda = new JMenu("Ayuda"); //crea un JMenu
		menuAyuda.setMnemonic(KeyEvent.VK_H);
		menuAyuda.getAccessibleContext().setAccessibleDescription("Menu para obtener ayuda sobre el uso del sistema");
		menuBar.add(menuAyuda);//lo agrega al menu
		
		JMenuItem manualItem = new JMenuItem("Manual de usuario",KeyEvent.VK_U); //crea un JMenuItem
		manualItem.getAccessibleContext().setAccessibleDescription( "Presione para abrir el manual de usuario");
		menuAyuda.add(manualItem);//lo agrega al menu
		manualItem.addActionListener(new ActionListener(){//agrega un listener para escuchar los clicks
			@Override
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				//TODO Abrir pdf
				
			    try {
			    	File path = new File (RUTA_MANUAL_DE_USUARIO); //Abre el archivo
					Desktop.getDesktop().open(path);
				} catch (IOException e1) {
					HandleResponseUtil.showMessageError("Error al Manual de usuario");
				}
			}	
		});
		
		setJMenuBar(menuBar);
	}

	private void cerrarSesion() {
		LoginView lw = new LoginView(); //crea un LoginVIew
		LoginService.logout(); //se deslogea
		lw.setVisible(true); //muestra el LoginView
		dispose(); //cierra la pantalla
	}
}
