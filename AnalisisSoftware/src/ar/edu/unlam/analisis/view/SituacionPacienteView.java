package ar.edu.unlam.analisis.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ar.edu.unlam.analisis.login.UserProvider;
import ar.edu.unlam.analisis.pacientes.controlpac;
import ar.edu.unlam.analisis.util.HandleResponseUtil;
import ar.edu.unlam.analisis.validator.TextValidator;

@SuppressWarnings("serial")
public class SituacionPacienteView extends JFrame{
	private JTextField textFieldCodigoPaciente;
	private JTextField textFieldCodigoMedico;
	private JTextArea textAreaDiagnostico;
	public SituacionPacienteView() {
		setSize(new Dimension(500, 500));//setea la dimension de la ventana
		setLocationRelativeTo(null);//setea que no este relativa a nada la pantalla
		JLabel lblSituacionDelPaciente = new JLabel("Situacion Del Paciente");//crea un label
		lblSituacionDelPaciente.setBounds(142, 17, 185, 16);//con esas medidas
		
		JLabel lblCodigoPaciente = new JLabel("Codigo Paciente:");//crea un label
		lblCodigoPaciente.setBounds(32, 56, 105, 16);//con esas medidas
		
		JLabel lblCodigoMedico = new JLabel("Codigo Medico:");//crea un label
		lblCodigoMedico.setBounds(32, 98, 105, 16);//con esas medidas
		
		JLabel lblDiagnostico = new JLabel("Diagnostico:");//crea un label
		lblDiagnostico.setBounds(32, 147, 105, 16);//con esas medidas
		
		textFieldCodigoPaciente = new JTextField(); //crea un textfield
		textFieldCodigoPaciente.setBounds(142, 51, 203, 26); //con esas medidas
		textFieldCodigoPaciente.setColumns(10); //setea las columnas del textField
		
		textFieldCodigoMedico = new JTextField();//crea un textfield
		textFieldCodigoMedico.setBounds(142, 93, 203, 26);//con esas medidas
		textFieldCodigoMedico.setColumns(10); //setea las columnas del textField
		
		textAreaDiagnostico = new JTextArea();//crea un textarea
		textAreaDiagnostico.setBounds(142, 147, 203, 72);//con esas medidas
		
		JButton btnLimpiar = new JButton("Limpiar");//crea el boton
		btnLimpiar.setToolTipText("Presione este botón para vaciar los campos");//agrega el tooltip text
		btnLimpiar.setBounds(72, 231, 117, 29); //setea las medidas del boton
		btnLimpiar.addActionListener(new ActionListener() {//agrega un listener para escuchar el clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				limpiar();//limpia la pantalla
			}
		});
		
		JButton btnRegistrar = new JButton("Registrar");//crea el boton
		btnRegistrar.setToolTipText("Presione este botón para registrar una nueva situación de un paciente atendida por un médico");//agrega el tooltip text
		btnRegistrar.setBounds(259, 231, 117, 29);//setea las medidas del boton
		btnRegistrar.addActionListener(new ActionListener() {//agrega un listener para escuchar el clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				StringBuilder errors = new StringBuilder(""); // Para mostrar los posibles errores
				
				if(textFieldCodigoMedico.getText().isEmpty()){ //Si esta vacio el textfield del codigo del medico:
					errors.append("*El codigo del medico es necesario.").append("\n"); //mensaje mostrado
				}
				if(textFieldCodigoPaciente.getText().isEmpty()){//Si esta vacio el textfield del codigo del paciente:
					errors.append("*El codigo del paciente es necesario.").append("\n"); //mensaje mostrado
				}
				
				if(textAreaDiagnostico.getText().isEmpty()){ //Si esta vacio el textarea de diagnostico
					errors.append("*El diagnostico es necesario.").append("\n");//mensaje mostrado
				}
				
			////VALIDACIONES DE CAJA NEGRA.
				errors.append(TextValidator.validarNumero(textFieldCodigoMedico.getText(),"codigo de medico"));
				errors.append(TextValidator.validarNumero(textFieldCodigoPaciente.getText(),"codigo de paciente"));
				errors.append(TextValidator.validarTexto(textAreaDiagnostico.getText(),50,"el diagnostico"));
				
				if(errors.toString().isEmpty()){ //Si no hay errores:
					try {
						controlpac.nuevaSituacionPaciente(textFieldCodigoPaciente.getText(), textFieldCodigoMedico.getText(), textAreaDiagnostico.getText(),true);//tomar los datos del textfield y textarea
						limpiar(); //limpiar la pantalla
						HandleResponseUtil.showMessageSuccess(HandleResponseUtil.COMMON_SUCCESS); //mostrar mensaje de exito 
					} catch (Exception e1) {
						HandleResponseUtil.showMessageError(HandleResponseUtil.COMMON_ERROR); // mostrar mensaje de error
					}
				}else{
					HandleResponseUtil.showMessageError(errors.toString());// mostrar mensaje de error
				}
			}
		});
		
		JButton btnVolver = new JButton("Volver");//crea el boton volver
		btnVolver.setToolTipText("Presione este botón para volver al menú anterior");//crea el tooltip text
		btnVolver.setBounds(384, 300, 117, 29); //setea las medidas del boton
		btnVolver.addActionListener(new ActionListener() {//crea listener para escuchar clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				IngresoDatosView idw = new IngresoDatosView(); //crea la pantalla de ingreso de datos
				idw.setVisible(true); //muestra la pantalla de ingreso de datos
				dispose();//cierra la pantalla de ingreso de datos del medico
			}
		});
		getContentPane().setLayout(null); //setea un layout absoluto
		getContentPane().add(lblSituacionDelPaciente);//agrega el label a la ventana
		getContentPane().add(lblCodigoPaciente);//agrega el label a la ventana
		getContentPane().add(lblCodigoMedico);//agrega el label a la ventana
		getContentPane().add(lblDiagnostico);//agrega el label a la ventana
		getContentPane().add(textFieldCodigoPaciente);//agrega el textarea a la ventana
		getContentPane().add(textFieldCodigoMedico);//agrega el textarea a la ventana
		getContentPane().add(textAreaDiagnostico);//agrega el textarea a la ventana
		getContentPane().add(btnLimpiar);//agrega el boton a la ventana
		getContentPane().add(btnRegistrar);//agrega el boton a la ventana
		getContentPane().add(btnVolver);//agrega el boton a la ventana
		
		JLabel label = new JLabel("Usuario: "+UserProvider.getUsuarioLogueado());//crea un label
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 10));//setea la fuente del label
		label.setBounds(6, 17, 90 + label.getText().length(), 16);//setea las medidas del label
		getContentPane().add(label);//agrega el label a la ventana
	}
	
	public void limpiar(){ //metodo para limpiar la pantalla
		textFieldCodigoMedico.setText(""); //limpia el textfield
		textFieldCodigoPaciente.setText("");//limpia el textfield
		textAreaDiagnostico.setText("");//limpia el textfield
	}
}
