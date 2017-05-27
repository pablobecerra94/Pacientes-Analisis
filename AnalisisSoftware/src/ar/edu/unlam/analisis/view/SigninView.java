package ar.edu.unlam.analisis.view;

import javax.swing.JFrame;
import javax.swing.JTextField;

import ar.edu.unlam.analisis.login.LoginService;
import ar.edu.unlam.analisis.pacientes.controlpac;
import ar.edu.unlam.analisis.util.HandleResponseUtil;

import javax.swing.JPasswordField;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SigninView extends JFrame{
	private JTextField usrText;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldVerify;
	public SigninView() {
		getContentPane().setLayout(null);
		setSize(new Dimension(500, 500));
		usrText = new JTextField();
		usrText.setBounds(174, 55, 130, 26);
		getContentPane().add(usrText);
		usrText.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 100, 130, 26);
		getContentPane().add(passwordField);
		
		passwordFieldVerify = new JPasswordField();
		passwordFieldVerify.setBounds(174, 138, 130, 26);
		getContentPane().add(passwordFieldVerify);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(16, 60, 61, 16);
		getContentPane().add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setBounds(16, 105, 84, 16);
		getContentPane().add(lblContrasea);
		
		JLabel lblVerificarContrasea = new JLabel("Verificar Contraseña:");
		lblVerificarContrasea.setBounds(16, 143, 146, 16);
		getContentPane().add(lblVerificarContrasea);
		
		JLabel lblRegistrarse = new JLabel("Registrarse");
		lblRegistrarse.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblRegistrarse.setBounds(172, 16, 115, 27);
		getContentPane().add(lblRegistrarse);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usrText.setText("");
				passwordField.setText("");
				passwordFieldVerify.setText("");
			}
		});
		btnLimpiar.setBounds(57, 179, 117, 29);
		getContentPane().add(btnLimpiar);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder errors = new StringBuilder("");
				if(usrText.getText().isEmpty()){
					errors.append("*El usuario es necesario").append("\n");
				}
				String ps1 = new String(passwordField.getPassword());
				if(ps1.isEmpty()){
					errors.append("*La constraseña es necesaria").append("\n");
				}else{
					if(ps1.length()<6){
						errors.append("La constraseña debe poseer al menos 6 caracteres").append("\n");
					}else{
						String ps2 = new String(passwordFieldVerify.getPassword());
						if(!ps1.equals(ps2)){
							errors.append("Las constraseñas no coinciden").append("\n");
						}
					}
				}
				
				if(errors.toString().isEmpty()){
					try {
						String respuesta = LoginService.signin(usrText.getText(), ps1);
						if(!respuesta.isEmpty()){
							HandleResponseUtil.showMessageError(respuesta);
						}else{
							HandleResponseUtil.showMessageSuccess("Usuario registrado con exito. Bienvenido: "+ usrText.getText());
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
		btnRegistrarse.setBounds(230, 179, 117, 29);
		getContentPane().add(btnRegistrarse);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				System.exit(0);
			}
		});
		btnSalir.setBounds(6, 243, 117, 29);
		getContentPane().add(btnSalir);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView lw = new LoginView();
				lw.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(327, 243, 117, 29);
		getContentPane().add(btnVolver);
	}
}
