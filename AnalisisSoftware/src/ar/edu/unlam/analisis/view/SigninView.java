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
public class SigninView extends JFrame{
	private JTextField usrText;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldVerify;
	public SigninView() {
		getContentPane().setLayout(null);//setea un layout absoluto
		setSize(new Dimension(500, 500));//setea la dimension de la ventana
		usrText = new JTextField(); //crea un textfield
		usrText.setBounds(174, 55, 130, 26); //setea las medidas del textfield
		getContentPane().add(usrText);//agrega el textfield a la ventana
		usrText.setColumns(10);//setea 10 columnas al textfield
		
		passwordField = new JPasswordField(); //crea un PasswordField
		passwordField.setBounds(174, 100, 130, 26);//setea las medidas del PasswordField
		getContentPane().add(passwordField);//agrega el passwordfield a la ventana
		
		passwordFieldVerify = new JPasswordField();//crea un PasswordField
		passwordFieldVerify.setBounds(174, 138, 130, 26);//setea las medidas del PasswordField
		getContentPane().add(passwordFieldVerify);//agrega el passwordfield a la ventana
		
		JLabel lblUsuario = new JLabel("Usuario:");//crea un label
		lblUsuario.setBounds(16, 60, 61, 16);//setea las dimensiones
		getContentPane().add(lblUsuario);//lo agrega a la ventana
		
		JLabel lblContrasea = new JLabel("Contraseña:");//crea un label
		lblContrasea.setBounds(16, 105, 84, 16);//setea las dimensiones
		getContentPane().add(lblContrasea);//lo agrega a la ventana
		
		JLabel lblVerificarContrasea = new JLabel("Verificar Contraseña:");//crea un label
		lblVerificarContrasea.setBounds(16, 143, 146, 16);//setea las dimensiones
		getContentPane().add(lblVerificarContrasea);//lo agrega a la ventana
		
		JLabel lblRegistrarse = new JLabel("Registrarse");//crea un label
		lblRegistrarse.setFont(new Font("Lucida Grande", Font.PLAIN, 16));//setea la fuente
		lblRegistrarse.setBounds(172, 16, 115, 27);//setea las dimensiones
		getContentPane().add(lblRegistrarse);//lo agrega a la ventana
		
		JButton btnLimpiar = new JButton("Limpiar");//crea el boton
		btnLimpiar.setToolTipText("Presione este botón para vaciar los campos");//agrega el tooltip text
		btnLimpiar.addActionListener(new ActionListener() {//agrega un listener para escuchar el clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				usrText.setText("");//inicializa al textfield vacio
				passwordField.setText("");//inicializa al passwordfield vacio
				passwordFieldVerify.setText("");//inicializa al passwordfield vacio
			}
		});
		btnLimpiar.setBounds(57, 179, 117, 29); //setea las medidas del boton
		getContentPane().add(btnLimpiar);//lo agrega a la ventana
		
		JButton btnRegistrarse = new JButton("Registrarse");//crea el boton
		btnRegistrarse.setToolTipText("Presione este botón para registrar un nuevo usuario");//agrega el tooltip text
		btnRegistrarse.addActionListener(new ActionListener() {//agrega un listener para escuchar el clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				StringBuilder errors = new StringBuilder(""); // Para mostrar los posibles errores
				if(usrText.getText().isEmpty()){ //si el textfield esta vacio:
					errors.append("*El usuario es necesario").append("\n"); //mensaje mostrado
				}
				String ps1 = new String(passwordField.getPassword()); //se toma la contraseña del passwordfield
				if(ps1.isEmpty()){//si no hay contraseña
					errors.append("*La constraseña es necesaria").append("\n");//mensaje mostrado
				}else{
					if(ps1.length()<6){//si la contraseña es menor a 6 caracteres:
						errors.append("La constraseña debe poseer al menos 6 caracteres").append("\n");//mensaje mostrado
					}else{
						String ps2 = new String(passwordFieldVerify.getPassword());//se toma la contraseña del passwordfield
						if(!ps1.equals(ps2)){ //si las 2 contraseñas ingresadas son distintas:
							errors.append("Las constraseñas no coinciden").append("\n");//mensaje mostrado
						}
					}
				}
				
				if(errors.toString().isEmpty()){//si no hay errores
					try {
						String respuesta = LoginService.signin(usrText.getText(), ps1);//string para mostrar los errores
						if(!respuesta.isEmpty()){//si hay respuesta de error:
							HandleResponseUtil.showMessageError(respuesta); //muestro mensaje de error
						}else{
							HandleResponseUtil.showMessageSuccess("Usuario registrado con exito. Bienvenido: "+ usrText.getText());//muestra mensaje de registro exitoso
							LoginService.login(usrText.getText(), ps1);//logea al usuario
							MainView mw = new MainView(); //crea un MainView
							mw.setVisible(true); //muestra el MainVIew
							dispose();//cierra la pantalla
							
						}
					} catch (Exception e1) {
						HandleResponseUtil.showMessageError("Se ha producido un error, ejecute el programa como administrador");
					}
				}else{
					HandleResponseUtil.showMessageError(errors.toString());
				}
			}
		});
		btnRegistrarse.setBounds(230, 179, 117, 29);//setea las medidas del boton
		getContentPane().add(btnRegistrarse);//lo agrega a la ventana
		
		JButton btnSalir = new JButton("Salir");//crea un boton
		btnSalir.setToolTipText("Presione este boton para cerrar el programa");//crea el tooltip text
		btnSalir.addActionListener(new ActionListener() {//crea listener para escuchar clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				setDefaultCloseOperation(EXIT_ON_CLOSE); //al tocar "Salir" se cierra la pantalla
				System.exit(0);
			}
		});
		btnSalir.setBounds(6, 243, 117, 29);//setea las medidas
		getContentPane().add(btnSalir);//lo agrega a la ventana
		
		JButton btnVolver = new JButton("Volver");//crea el boton volver
		btnVolver.setToolTipText("Presione este botón para volver al menú anterior");//crea el tooltip text
		btnVolver.addActionListener(new ActionListener() {//crea listener para escuchar clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				LoginView lw = new LoginView();//crea la pantalla de login
				lw.setVisible(true);//muestra la pantalla de login
				dispose();//cierra la pantalla
			}
		});
		btnVolver.setBounds(327, 243, 117, 29);//setea las medidas del boton
		getContentPane().add(btnVolver);//lo agrega a la ventana
	}
}
