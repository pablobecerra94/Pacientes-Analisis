package ar.edu.unlam.analisis.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import ar.edu.unlam.analisis.combo.ComboItem;
import ar.edu.unlam.analisis.login.UserProvider;
import ar.edu.unlam.analisis.pacientes.controlpac;
import ar.edu.unlam.analisis.util.HandleResponseUtil;
import ar.edu.unlam.analisis.validator.TextValidator;
import ar.edu.unlam.analisis.wrapper.ComboWrapper;

@SuppressWarnings("serial")
public class SituacionPacienteView extends JFrame{
	private JTextArea textAreaDiagnostico;
	private JComboBox<ComboItem> comboPacientes;
	JComboBox<ComboItem> comboMedicos;
	JScrollPane scroll;
	
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
		lblDiagnostico.setBounds(32, 147, 105, 16);
		
		textAreaDiagnostico = new JTextArea();//crea un textarea
		textAreaDiagnostico.setBounds(142, 147, 203, 72);//con esas medidas
		 scroll = new JScrollPane(textAreaDiagnostico); //agregado de scroll
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //agregado de scroll
	    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS); //agregado de scroll
	    scroll.setVisible(true); //agregado de scroll
	    scroll.setBounds(142, 147, 203, 72); //agregado de scroll
		
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
				
				ComboItem selectedMedico = (ComboItem)comboMedicos.getSelectedItem();
				ComboItem selectedPaciente = (ComboItem)comboPacientes.getSelectedItem();
				
				if(!ComboWrapper.isSeleccionado(selectedMedico.getKey())){ //Si esta vacio el textfield del codigo del medico:
					errors.append("*El medico es necesario.").append("\n"); //mensaje mostrado
				}
				if(!ComboWrapper.isSeleccionado(selectedPaciente.getKey())){//Si esta vacio el textfield del codigo del paciente:
					errors.append("*El paciente es necesario.").append("\n"); //mensaje mostrado
				}
				
				if(textAreaDiagnostico.getText().isEmpty()){ //Si esta vacio el textarea de diagnostico
					errors.append("*El diagnostico es necesario.").append("\n");//mensaje mostrado
				}
				
			////VALIDACIONES DE CAJA NEGRA.
				errors.append(TextValidator.validarTexto(textAreaDiagnostico.getText(),50,"diagnostico"));
				
				if(errors.toString().isEmpty()){ //Si no hay errores:
					try {
						controlpac.nuevaSituacionPaciente(selectedPaciente.getKey(), selectedMedico.getKey(), textAreaDiagnostico.getText(),true);//tomar los datos del textfield y textarea
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
		getContentPane().add(lblDiagnostico);
		getContentPane().add(scroll); //agrega el textarea a la ventana
		getContentPane().add(btnLimpiar);//agrega el boton a la ventana
		getContentPane().add(btnRegistrar);//agrega el boton a la ventana
		getContentPane().add(btnVolver);//agrega el boton a la ventana
	
		JLabel label = new JLabel("Usuario: "+UserProvider.getUsuarioLogueado());//crea un label
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 10));//setea la fuente del label
		label.setBounds(6, 17, 90 + label.getText().length(), 16);//setea las medidas del label
		getContentPane().add(label);//agrega el label a la ventana
		
		try {
			comboPacientes = ComboWrapper.getCombo(controlpac.getTodosLosPacientes()); //agrega los pacientes al combo
		} catch (IOException e1) { //
			comboPacientes = ComboWrapper.getComboVacio(); // combo vacio
		}
		
		comboPacientes.setBounds(152, 52, 156, 27); //medidas
		getContentPane().add(comboPacientes); //agregado de combo
		
		try {
			comboMedicos = ComboWrapper.getCombo(controlpac.getTodosLosMedicos()); //agrega los pacientes al combo
		} catch (IOException e1) { //
			comboMedicos = ComboWrapper.getComboVacio(); // combo vacio
		}
		
		comboMedicos.setBounds(149, 94, 159, 27);  //medidas
		getContentPane().add(comboMedicos);  //agregado de combo
	}
	
	public void limpiar(){ //metodo para limpiar la pantalla
		comboPacientes.setSelectedIndex(0); //limpia el combo
		comboMedicos.setSelectedIndex(0);//limpia el combo
		textAreaDiagnostico.setText("");//limpia el textfield
	}
}
