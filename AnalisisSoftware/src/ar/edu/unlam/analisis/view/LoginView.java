package ar.edu.unlam.analisis.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ar.edu.unlam.analisis.login.LoginService;
import ar.edu.unlam.analisis.util.HandleResponseUtil;

@SuppressWarnings("serial")
public class LoginView extends JFrame{
	private JTextField usrText;
	private JPasswordField passwordField;
	public LoginView() {
		getContentPane().setLayout(null);//setea un layout absoluto
		setSize(new Dimension(500, 500));//setea la dimension de la ventana
		usrText = new JTextField(); //crea un TextField
		usrText.setBounds(187, 62, 130, 26); //setea las medidas 
		getContentPane().add(usrText); //lo agrega a la pantalla
		usrText.setColumns(10); //setea 10 columnas 
		
		JLabel lblUsuario = new JLabel("Usuario:");//crea un label
		lblUsuario.setBounds(70, 67, 61, 16); //setea las medidas 
		getContentPane().add(lblUsuario);//lo agrega a la pantalla
		
		JLabel lblContrasenia = new JLabel("Contraseña:");//crea un label
		lblContrasenia.setBounds(70, 117, 88, 16);//setea las medidas 
		getContentPane().add(lblContrasenia);//lo agrega a la pantalla
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesión");//crea un boton
		btnIniciarSesion.setToolTipText("Presione este boton para ingresar al sistema");//agrega el tooltip text
		btnIniciarSesion.addActionListener(new ActionListener() {//agrega un listener para escuchar el clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				StringBuilder errors = new StringBuilder(""); //String para los errores
				if(usrText.getText().isEmpty()){ //si el textfield esta vacio
					errors.append("*El usuario es necesario").append("\n"); //mostrar mensaje
				}
				if(new String(passwordField.getPassword()).isEmpty()){//si el passwordfield esta vacio
					errors.append("*La constraseña es necesaria").append("\n");//mostrar mensaje
				}
				
				if(errors.toString().isEmpty()){ //si no hay errores
						try {
							String respuesta = LoginService.login(usrText.getText(), new String(passwordField.getPassword()));
							if(!respuesta.isEmpty()){
								HandleResponseUtil.showMessageError(respuesta);
							}else{
								MainView mw = new MainView(); //crea un MainView
								mw.setVisible(true);//muestra el MainView
								dispose(); //cierra la pantalla
							}
						} catch (Exception e1) {
							HandleResponseUtil.showMessageError(e1.getMessage());
						}
				}else{
					HandleResponseUtil.showMessageError(errors.toString());
				}
			}
		});
		btnIniciarSesion.setBounds(199, 160, 117, 29);//setea las medidas 
		getContentPane().add(btnIniciarSesion);//lo agrega a la pantalla
		
		JButton btnRegistrarse = new JButton("Registrarse");//crea un boton
		btnRegistrarse.setToolTipText("Presione este botón para registrar un nuevo usuario");//agrega el tooltip text
		btnRegistrarse.addActionListener(new ActionListener() {//agrega un listener para escuchar el clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				SigninView sw = new SigninView(); //crea un SigninView
				sw.setVisible(true); //muestra el SigninView
				dispose(); //cierra la pantalla
			}
		});
		btnRegistrarse.setBounds(327, 243, 117, 29);//setea las medidas 
		getContentPane().add(btnRegistrarse);//lo agrega a la pantalla
		
		JButton btnLimpiar = new JButton("Limpiar");//crea un boton
		btnLimpiar.setToolTipText("Presione este botón para vaciar los campos");//agrega el tooltip text
		btnLimpiar.addActionListener(new ActionListener() {//agrega un listener para escuchar el clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				passwordField.setText("");//setea al passwordField como vacio
				usrText.setText("");//setea el textfield como vacio
			}
		});
		btnLimpiar.setBounds(70, 160, 117, 29);//setea las medidas 
		getContentPane().add(btnLimpiar);//lo agrega a la pantalla
		
		JButton btnSalir = new JButton("Salir");//crea un boton
		btnSalir.setToolTipText("Presione este boton para cerrar el programa");//agrega el tooltip text
		btnSalir.addActionListener(new ActionListener() {//agrega un listener para escuchar el clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				setDefaultCloseOperation(EXIT_ON_CLOSE); // cierra la pantalla si se clikea el boton
				System.exit(0);
			}
		});
		btnSalir.setBounds(6, 243, 117, 29);//setea las medidas 
		getContentPane().add(btnSalir);//lo agrega a la pantalla
		
		passwordField = new JPasswordField();//crea un PasswordField
		passwordField.setBounds(187, 112, 130, 26);//setea las medidas 
		getContentPane().add(passwordField);//lo agrega a la pantalla
		
		JLabel lblBienvenidoAlCentro = new JLabel("Bienvenido al Centro Medico Los Laureles");//crea un label
		lblBienvenidoAlCentro.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblBienvenidoAlCentro.setBounds(69, 6, 381, 16);//setea las medidas 
		getContentPane().add(lblBienvenidoAlCentro);//lo agrega a la pantalla
		
		JLabel lblnoTenesUsuario = new JLabel("¿No tenes Usuario?");//crea un label
		lblnoTenesUsuario.setBounds(193, 248, 124, 16);//setea las medidas 
		getContentPane().add(lblnoTenesUsuario);//lo agrega a la pantalla
	}
}
