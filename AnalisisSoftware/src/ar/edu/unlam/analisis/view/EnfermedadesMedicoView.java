package ar.edu.unlam.analisis.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ar.edu.unlam.analisis.combo.ComboItem;
import ar.edu.unlam.analisis.enums.ETipoInforme;
import ar.edu.unlam.analisis.login.UserProvider;
import ar.edu.unlam.analisis.pacientes.controlpac;
import ar.edu.unlam.analisis.util.HandleResponseUtil;
import ar.edu.unlam.analisis.wrapper.ComboWrapper;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class EnfermedadesMedicoView extends JFrame{
	private JList<String> listEspecialidades;
	private JScrollPane scrollPaneEspecialidades;
	private JComboBox<ComboItem> comboMedicos;
	public EnfermedadesMedicoView() {
		getContentPane().setLayout(null); //setea el layout absoluto
		setSize(new Dimension(500, 500)); //setea las dimensiones
		setLocationRelativeTo(null); //setea que no sea relativo a nada
		JButton btnVolver = new JButton("Volver"); //crea el boton volver
		btnVolver.setToolTipText("Presione este botón para volver al menú anterior"); //setea el tooltip
		btnVolver.addActionListener(new ActionListener() { //crea el listener para escuchar clicks
			public void actionPerformed(ActionEvent e) { //metodo para escuchar clicks
				InformesView iw = new InformesView(); //crea la vista de informes
				iw.setVisible(true); //muestra la vista de informes
				dispose(); //cierra la ventana actual
			}
		});
		btnVolver.setBounds(361, 291, 117, 29); //setea las dimensiones
		getContentPane().add(btnVolver); //agrega el boton a la ventana
		
		JLabel lblCodigoMedico = new JLabel("Codigo Medico:"); //crea el label
		lblCodigoMedico.setBounds(1, 65, 105, 16); ///setea las dimensiones
		getContentPane().add(lblCodigoMedico);
		
		JButton btnBuscar = new JButton("Buscar"); //crea el boton buscar
		btnBuscar.setToolTipText("Presione este boton para ver las enfermedades tratadas por el medico ingresado"); //setea el tooltip
		btnBuscar.addActionListener(new ActionListener() { //crea el listener para clicks
			public void actionPerformed(ActionEvent e) { //metodo para clicks
				
				if(!ComboWrapper.isSeleccionado(((ComboItem)comboMedicos.getSelectedItem()).getKey())){ //si el combo fue seleccionado
					HandleResponseUtil.showMessageError("El médico es necesario"); //muestra mensaje de error
					return; //termina
				}
				
				try {
					String selected = ComboWrapper.getSelectedKey(comboMedicos);
					Collection<String> col = controlpac.getInformeMedico(selected, ETipoInforme.ESPECIALIDADES); //crea una coleccion de los informes de los medicos
					DefaultListModel listModel = new DefaultListModel(); //crea un list model
					for(String s: col){ //por cada informe
						listModel.addElement(s); //lo agrega al list
					}
					listEspecialidades.setModel(listModel); //setea que la lista de especialidades sea el list model
					listEspecialidades.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION); //setea que se pueda seleccionar uno solo
					scrollPaneEspecialidades.setViewportView(listEspecialidades); //agrega scrollpane
				} catch (Exception e1) {
					HandleResponseUtil.showMessageError("Se ha producido un error");
				}
			}
		});
		btnBuscar.setBounds(260, 60, 117, 29); //setea las dimensiones
		getContentPane().add(btnBuscar); //agrega el boton buscar a la ventana
		
		JButton btnLimpiar = new JButton("Limpiar"); //crea el boton limpiar
		btnLimpiar.setToolTipText("Presione este botón para vaciar los campos"); //setea el tooltip
		btnLimpiar.addActionListener(new ActionListener() { //listener para escuchar clicks
			public void actionPerformed(ActionEvent e) { //metodo para clicks
				comboMedicos.setSelectedIndex(0); //vacia el campo
				DefaultListModel listModel = new DefaultListModel(); //crea un model vacio
				listEspecialidades.setModel(listModel); //setea el model vacio
				scrollPaneEspecialidades.setViewportView(listEspecialidades); //agrega scroll
			}
		});
		btnLimpiar.setBounds(377, 60, 117, 29); //setea dimensiones
		getContentPane().add(btnLimpiar); //agrega boton limpiar
		
		 scrollPaneEspecialidades = new JScrollPane(); //agrega scrollpane
		scrollPaneEspecialidades.setBounds(77, 105, 244, 162); //setea dimensiones
		getContentPane().add(scrollPaneEspecialidades); //agrega scroll a ventana
		
		 listEspecialidades = new JList<String>(); //agrega lista de especialidades
		scrollPaneEspecialidades.setViewportView(listEspecialidades); //setea el scroll
		
		JLabel lblEspecialidadesDelMedico = new JLabel("Especialidades del Medico"); //crea label
		lblEspecialidadesDelMedico.setFont(new Font("Lucida Grande", Font.PLAIN, 16)); //setea la fuente
		lblEspecialidadesDelMedico.setBounds(118, 19, 238, 16); //setea las dimensiones
		getContentPane().add(lblEspecialidadesDelMedico); //agrega label 
		JLabel label = new JLabel("Usuario: "+UserProvider.getUsuarioLogueado()); //crea label
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 10)); //setea fuente
		label.setBounds(6, 17, 90 + label.getText().length(), 16); //setea dimensiones
		getContentPane().add(label); //agrega label
		
		try {
			comboMedicos = ComboWrapper.getCombo(controlpac.getTodosLosMedicos());
		} catch (IOException e1) {
			comboMedicos = ComboWrapper.getComboVacio();
		}finally{
		comboMedicos.setBounds(116, 61, 150, 27);
		getContentPane().add(comboMedicos);
		}
	}
}
