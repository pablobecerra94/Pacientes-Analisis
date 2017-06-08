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
public class DatosPacienteView extends JFrame {
	private JTextField textFieldCodigo;
	private JTextField textFieldNombre;
	
	public DatosPacienteView() {
		getContentPane().setLayout(null); //setea un layout absoluto
		setSize(new Dimension(500, 500));  //setea la dimension de la ventana
		setLocationRelativeTo(null); //setea que no este relativa a nada la pantalla
		JButton btnRegistrar = new JButton("Registrar"); //crea un boton para registrar
		btnRegistrar.setToolTipText("Presione este botón para guardar un nuevo paciente con los datos ingresados"); //Crea el tooltip
		btnRegistrar.addActionListener(new ActionListener() { //crea el listener para escuchar clicks
			public void actionPerformed(ActionEvent e) { //metodo para escuchar clicks
				StringBuilder errors = new StringBuilder(""); //builder de string para errores
				if(textFieldCodigo.getText().isEmpty()){ //si el codigo esta vacio
					errors.append("*El codigo es necesario").append("\n"); //agrega el error
				}
				if(textFieldNombre.getText().isEmpty()){ //si el nombre esta vacio
					errors.append("*El nombre es necesario").append("\n"); //agrega el error
				}
				
				if(errors.toString().isEmpty()){ //si la lista de errores esta vacia
					try {
						controlpac.nuevoPaciente(textFieldCodigo.getText(), textFieldNombre.getText(),true); //crea el paciente
						limpiar(); //limpia la pantalla
						HandleResponseUtil.showMessageSuccess(HandleResponseUtil.COMMON_SUCCESS); //muestra el mensaje de exito
					} catch (Exception e1) {
						HandleResponseUtil.showMessageError(HandleResponseUtil.COMMON_ERROR); //muestra el mensaje de error
					}
				}else{
					HandleResponseUtil.showMessageError(errors.toString()); //muestra el mensaje de error
				}
			}
		});
		btnRegistrar.setBounds(264, 132, 117, 29); //agrega las dimensiones
		getContentPane().add(btnRegistrar); //agrega el boton a la ventana
		
		JButton btnLimpiar = new JButton("Limpiar"); //crea un boton para limpiar
		btnLimpiar.setToolTipText("Presione este botón para vaciar los campos"); //setea el tooltip
		btnLimpiar.addActionListener(new ActionListener() { //agrega el listener para escuchar clicks
			public void actionPerformed(ActionEvent e) { //metodo para escuchar clicks
				limpiar(); //limpia la pantalla 
			}
		});
		btnLimpiar.setBounds(86, 132, 117, 29); //setea las dimensiones
		getContentPane().add(btnLimpiar); //limpia la pantalla
		
		JButton btnVolver = new JButton("Volver"); //crea el boton para volver
		btnVolver.setToolTipText("Presione este botón para volver al menú anterior"); //setea el tooltip
		btnVolver.addActionListener(new ActionListener() { //agrega el listener para escuchar clicks
			public void actionPerformed(ActionEvent e) { //metodo para eschuchar clicks
				IngresoDatosView idw = new IngresoDatosView(); //crea la ventana de ingreso de datos
				idw.setVisible(true); //muestra la pantalla
				dispose(); //cierra la pantalla actual
			}
		});
		btnVolver.setBounds(327, 243, 117, 29); //setea las dimensiones 
		getContentPane().add(btnVolver); //agrega el boton a la ventana
		
		textFieldCodigo = new JTextField(); //crea el campo para ingresar el codigo
		textFieldCodigo.setBounds(159, 45, 178, 26); //Setea las dimensiones
		getContentPane().add(textFieldCodigo); //agrega el campo a la ventana
		textFieldCodigo.setColumns(10); //setea las columnas 
		
		textFieldNombre = new JTextField(); //crea el campo para ingresar nombre
		textFieldNombre.setBounds(159, 79, 178, 26); //setea las dimensiones
		getContentPane().add(textFieldNombre); //agrega el campo a la ventana
		textFieldNombre.setColumns(10); //setea las columnas
		
		JLabel lblCodigo = new JLabel("Codigo:"); //crea el label para codigo
		lblCodigo.setBounds(86, 50, 61, 16); //setea las dimensiones
		getContentPane().add(lblCodigo); //agrega el label a la ventana
		
		JLabel lblNombre = new JLabel("Nombre:");//crea el label 
		lblNombre.setBounds(86, 84, 61, 16); //setea las dimensiones
		getContentPane().add(lblNombre); //agrega el label a la ventana
		
		JLabel lblNuevoPaciente = new JLabel("Nuevo Paciente"); //crea el label 
		lblNuevoPaciente.setBounds(183, 6, 103, 16); //setea las dimensiones
		getContentPane().add(lblNuevoPaciente); //agrega el label a la ventana
		
		JLabel label = new JLabel("Usuario: "+UserProvider.getUsuarioLogueado()); //crea el label 
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 10)); //setea la fuente
		label.setBounds(6, 17, 90 + label.getText().length(), 16);//setea las dimensiones
		getContentPane().add(label); //agrega el label a la ventana
	}
	
	public void limpiar(){ //metodo para limpiar la pantalla
		textFieldCodigo.setText(""); //setea los campos
		textFieldNombre.setText("");//con una cadena vacia
	}
}
