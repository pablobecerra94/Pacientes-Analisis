package ar.edu.unlam.analisis.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

@SuppressWarnings("serial")
public class PacientesPorMedicoView extends JFrame{
	private JList<String> listPacientes;
	private JScrollPane scrollPanePacientes;
	private JComboBox<ComboItem> comboMedicos;
	public PacientesPorMedicoView() {
		getContentPane().setLayout(null);//setea un layout absoluto
		setSize(new Dimension(500, 500));//setea la dimension de la ventana
		setLocationRelativeTo(null);//setea que no este relativa a nada la pantalla
		JButton btnVolver = new JButton("Volver"); //crea un boton
		btnVolver.setToolTipText("Presione este botón para volver al menú anterior");//crea el tooltip text
		btnVolver.addActionListener(new ActionListener() {//crea listener para escuchar clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				InformesView iw = new InformesView();//crea la pantalla de informes
				iw.setVisible(true);//muestra la pantall de informes
				dispose(); //cierra la pantalla
			}
		});
		btnVolver.setBounds(357, 294, 117, 29); //setea las medidas del boton
		getContentPane().add(btnVolver); //lo agrega a la ventana
		
		JButton btnBuscar = new JButton("Buscar"); //crea un boton
		btnBuscar.setToolTipText("Presione este boton para ver los pacientes atendidos por el medico ingresado");//crea el tooltip text
		btnBuscar.addActionListener(new ActionListener() {//crea listener para escuchar clicks
			@SuppressWarnings({ "unchecked", "rawtypes" }) //
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				
				if(!ComboWrapper.isSeleccionado(((ComboItem)comboMedicos.getSelectedItem()).getKey())){ //si el combo fue seleccionado
					HandleResponseUtil.showMessageError("El médico es necesario"); //muestra mensaje de error
					return; //termina
				}
				
				try {
					String selected = ComboWrapper.getSelectedKey(comboMedicos);
					Collection<String> col = controlpac.getInformeMedico(selected, ETipoInforme.PACIENTES);
					DefaultListModel listModel = new DefaultListModel(); //crea un defaultListModel
					for(String s: col){ //for each
						listModel.addElement(s);
					}
					listPacientes.setModel(listModel);
					listPacientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
					scrollPanePacientes.setViewportView(listPacientes);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnBuscar.setBounds(273, 49, 117, 29); //setea las medidas del boton
		getContentPane().add(btnBuscar); //lo agrega a la ventana
		
		JButton btnLimpiar = new JButton("Limpiar"); // crea un boton
		btnLimpiar.setToolTipText("Presione este botón para vaciar los campos");//crea el tooltip text
		btnLimpiar.addActionListener(new ActionListener() {//crea listener para escuchar clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				comboMedicos.setSelectedIndex(0);
				
				DefaultListModel listModel = new DefaultListModel(); //crea un DefaultListModel
				listPacientes.setModel(listModel);
				scrollPanePacientes.setViewportView(listPacientes);
			}
		});
		btnLimpiar.setBounds(383, 49, 117, 29); //setea las medidas del boton
		getContentPane().add(btnLimpiar);
		
		JLabel lblCodigoMedico = new JLabel("Codigo Medico:"); //crea un label
		lblCodigoMedico.setBounds(6, 54, 125, 16); //setea las medidas del label
		getContentPane().add(lblCodigoMedico);//lo agrega a la pantalla
		
		 scrollPanePacientes = new JScrollPane();//crea un scrollpanel
		scrollPanePacientes.setBounds(91, 108, 299, 158);//setea las medidas del scrollpanel
		getContentPane().add(scrollPanePacientes);//lo agrega a la pantalla
		
		listPacientes = new JList<String>();//crea un Jlist
		scrollPanePacientes.setViewportView(listPacientes);
		
		JLabel lblPacientesPorMedico = new JLabel("Pacientes por Medico");//crea un label
		lblPacientesPorMedico.setFont(new Font("Lucida Grande", Font.PLAIN, 16));//setea la fuente del label
		lblPacientesPorMedico.setBounds(171, 21, 246, 16);//setea las medias del label
		getContentPane().add(lblPacientesPorMedico);//lo agrega a la pantalla
		JLabel label = new JLabel("Usuario: "+UserProvider.getUsuarioLogueado());//crea un label
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 10));//setea la fuente del label
		label.setBounds(6, 17, 90 + label.getText().length(), 16);//setea las medias del label
		getContentPane().add(label);//lo agrega a la pantalla
		
		comboMedicos = new JComboBox<ComboItem>();
		try {
			comboMedicos = ComboWrapper.getCombo(controlpac.getTodosLosMedicos());
		} catch (IOException e1) {
			comboMedicos = ComboWrapper.getComboVacio();
		}finally{
		comboMedicos.setBounds(116, 50, 157, 27);
		getContentPane().add(comboMedicos);
		}
	}
	
	
}
