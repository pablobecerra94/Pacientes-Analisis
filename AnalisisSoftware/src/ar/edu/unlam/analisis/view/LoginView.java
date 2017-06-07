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
		getContentPane().setLayout(null);
		setSize(new Dimension(500, 500));
		usrText = new JTextField();
		usrText.setBounds(187, 62, 130, 26);
		getContentPane().add(usrText);
		usrText.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(70, 67, 61, 16);
		getContentPane().add(lblUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña:");
		lblContrasenia.setBounds(70, 117, 88, 16);
		getContentPane().add(lblContrasenia);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.setToolTipText("Presione este boton para ingresar al sistema");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder errors = new StringBuilder("");
				if(usrText.getText().isEmpty()){
					errors.append("*El usuario es necesario").append("\n");
				}
				if(new String(passwordField.getPassword()).isEmpty()){
					errors.append("*La constraseña es necesaria").append("\n");
				}
				
				if(errors.toString().isEmpty()){
						try {
							String respuesta = LoginService.login(usrText.getText(), new String(passwordField.getPassword()));
							if(!respuesta.isEmpty()){
								HandleResponseUtil.showMessageError(respuesta);
							}else{
								MainView mw = new MainView();
								mw.setVisible(true);
								dispose();
							}
						} catch (Exception e1) {
							HandleResponseUtil.showMessageError(e1.getMessage());
						}
				}else{
					HandleResponseUtil.showMessageError(errors.toString());
				}
			}
		});
		btnIniciarSesion.setBounds(199, 160, 117, 29);
		getContentPane().add(btnIniciarSesion);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setToolTipText("Presione este botón para registrar un nuevo usuario");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SigninView sw = new SigninView();
				sw.setVisible(true);
				dispose();
			}
		});
		btnRegistrarse.setBounds(327, 243, 117, 29);
		getContentPane().add(btnRegistrarse);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setToolTipText("Presione este botón para vaciar los campos");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordField.setText("");
				usrText.setText("");
			}
		});
		btnLimpiar.setBounds(70, 160, 117, 29);
		getContentPane().add(btnLimpiar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setToolTipText("Presione este boton para cerrar el programa");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				System.exit(0);
			}
		});
		btnSalir.setBounds(6, 243, 117, 29);
		getContentPane().add(btnSalir);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(187, 112, 130, 26);
		getContentPane().add(passwordField);
		
		JLabel lblBienvenidoAlCentro = new JLabel("Bienvenido al Centro Medico Los Laureles");
		lblBienvenidoAlCentro.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblBienvenidoAlCentro.setBounds(69, 6, 381, 16);
		getContentPane().add(lblBienvenidoAlCentro);
		
		JLabel lblnoTenesUsuario = new JLabel("¿No tenes Usuario?");
		lblnoTenesUsuario.setBounds(193, 248, 124, 16);
		getContentPane().add(lblnoTenesUsuario);
	}
}
