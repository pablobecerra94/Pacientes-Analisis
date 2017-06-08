package ar.edu.unlam.analisis.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ar.edu.unlam.analisis.login.UserProvider;
import ar.edu.unlam.analisis.pacientes.controlpac;
import ar.edu.unlam.analisis.util.HandleResponseUtil;

@SuppressWarnings("serial")
public class DatosMedicoView extends JFrame{
	private JTextField textCodigo;
	private JTextField textNombre;
	private JTextField textEspecializacion;
	public DatosMedicoView() {
		getContentPane().setLayout(null); //setea un layout absoluto
		setSize(new Dimension(500, 500)); //setea la dimension de la ventana
		setLocationRelativeTo(null); //setea que no este relativa a nada la pantalla
		JLabel lblNuevoMedico = new JLabel("Nuevo Medico"); //crea un label
		lblNuevoMedico.setBounds(160, 6, 100, 16); //con esas medidas
		getContentPane().add(lblNuevoMedico); //agregadandolo a la ventana
		
		textCodigo = new JTextField(); //crea el campo para ingresar codigo
		textCodigo.setBounds(140, 34, 130, 26); //setea las dimensiones
		getContentPane().add(textCodigo); //lo agrega a la ventana
		textCodigo.setColumns(10); //con 10 columnas 
		
		textNombre = new JTextField(); //crea el campo para ingresar nombre
		textNombre.setBounds(140, 65, 130, 26);  //setea las dimensiones
		getContentPane().add(textNombre); //lo agrega a la ventana
		textNombre.setColumns(10); //con 10 columnas 
		
		textEspecializacion = new JTextField(); //crea el campo para ingresar especializacion
		textEspecializacion.setBounds(140, 103, 130, 26); //setea las dimensiones
		getContentPane().add(textEspecializacion); //lo agrega a la ventana
		textEspecializacion.setColumns(10);  //con 10 columnas 
		
		JLabel lblCodigo = new JLabel("Codigo:"); //crea un label
		lblCodigo.setBounds(37, 39, 61, 16); //setea las dimensiones
		getContentPane().add(lblCodigo);  //lo agrega a la ventana
		 
		JLabel lblNombre = new JLabel("Nombre:"); //crea un label
		lblNombre.setBounds(37, 70, 61, 16); //setea las dimensiones
		getContentPane().add(lblNombre); //lo agrega a la ventana
		
		JLabel lblEspecializacion = new JLabel("Especializacion:"); //crea un label
		lblEspecializacion.setBounds(37, 108, 102, 16); //setea las dimensiones
		getContentPane().add(lblEspecializacion); //lo agrega a la ventana
		
		JButton btnLimpiar = new JButton("Limpiar"); //crea el boton
		btnLimpiar.setToolTipText("Presione este botón para vaciar los campos"); //agrega el tooltip text
		btnLimpiar.addActionListener(new ActionListener() { //agrega un listener para escuchar el clicks
			public void actionPerformed(ActionEvent e) { //metodo para escuchar clicks
				limpiar(); //limpia la pantalla
			}
		});
		btnLimpiar.setBounds(70, 154, 117, 29); //setea las dimensiones
		getContentPane().add(btnLimpiar); //lo agrega a la ventana
		
		JButton btnRegistrar = new JButton("Registrar"); //crea el boton
		btnRegistrar.setToolTipText("Presione este botón para guardar un nuevo médico con los datos ingresados"); //agrega el tooltip text
		btnRegistrar.addActionListener(new ActionListener() { //agrega un listener para escuchar el clicks
			public void actionPerformed(ActionEvent e) {  //metodo para escuchar clicks
				StringBuilder errors = new StringBuilder(""); //crea un builder de string
				if(textCodigo.getText().isEmpty()){ //si el campo codigo esta vacio
					errors.append("*El codigo es necesario.").append("\n"); //le agrega al string builder el error
				}
				if(textEspecializacion.getText().isEmpty()){ //si el campo especializacion esta vacio
					errors.append("*La especializacion es necesaria.").append("\n");  //le agrega al string builder el error
				}
				if(textNombre.getText().isEmpty()){  //si el campo nombre esta vacio
					errors.append("*El nombre es necesario.").append("\n"); //le agrega al string builder el error
				}
				
				if(errors.toString().isEmpty()){ //si no hay errores
					try {
						controlpac.nuevoMedico(textCodigo.getText(), textNombre.getText(), textEspecializacion.getText(),true); //crea el medico
						HandleResponseUtil.showMessageSuccess(HandleResponseUtil.COMMON_SUCCESS); //muestra el mensaje de exito
						limpiar(); //limpia la pantalla
					} catch (Exception e1) {
						HandleResponseUtil.showMessageError(HandleResponseUtil.COMMON_ERROR); //muesta el mensaje de error
					}
				}else{
					HandleResponseUtil.showMessageError(errors.toString()); //muestra los mensajes de error
				}
			}
		});
		btnRegistrar.setBounds(217, 154, 117, 29); //setea las dimensiones
		getContentPane().add(btnRegistrar); //lo agrega a la ventana
		
		JButton btnVolver = new JButton("Volver"); //crea el boton volver
		btnVolver.setToolTipText("Presione este botón para volver al menú anterior"); //crea el tooltip text
		btnVolver.addActionListener(new ActionListener() { //crea listener para escuchar clicks
			public void actionPerformed(ActionEvent e) { //metodo para escuchar clicks
				IngresoDatosView idw = new IngresoDatosView(); //crea la pantalla de ingreso de datos
				idw.setVisible(true); //muestra la pantall de ingreso de datos
				dispose(); //cierra la pantalla de ingreso de datos del medico
			}
		});
		btnVolver.setBounds(327, 243, 117, 29); //setea las dimensiones
		getContentPane().add(btnVolver); //agrega el boton volver
		JLabel label = new JLabel("Usuario: "+UserProvider.getUsuarioLogueado()); //crea uyn label
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		label.setBounds(6, 17, 90 + label.getText().length(), 16); //setea las dimensiones
		getContentPane().add(label); //lo agrega a la ventana
	}
	
	public void limpiar(){ //metodo para limpiar la pantalla 
		textCodigo.setText(""); //setea 
		textEspecializacion.setText(""); //todos los campos
		textNombre.setText(""); //con una cadena vacia
	}

}
