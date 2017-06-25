package ar.edu.unlam.analisis.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import ar.edu.unlam.analisis.combo.ComboItem;
import ar.edu.unlam.analisis.login.UserProvider;
import ar.edu.unlam.analisis.pacientes.controlpac;
import ar.edu.unlam.analisis.util.HandleResponseUtil;
import ar.edu.unlam.analisis.wrapper.ComboWrapper;

@SuppressWarnings("serial")
public class ListadoView extends JFrame{
	private JComboBox<ComboItem> comboTipoListado;
	private JScrollPane scrollPaneListado;
	private JList<String> listado;
	public ListadoView() {
		getContentPane().setLayout(null);
		setSize(new Dimension(500, 500));//setea la dimension de la ventana
		setLocationRelativeTo(null);//setea que no este relativa a nada la pantalla
		
		JLabel lblTipoDeListado = new JLabel("Tipo de Listado:");
		lblTipoDeListado.setBounds(26, 58, 107, 16);
		getContentPane().add(lblTipoDeListado);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ComboWrapper.isSeleccionado(((ComboItem)comboTipoListado.getSelectedItem()).getKey())){ //si el combo fue seleccionado
					HandleResponseUtil.showMessageError("El tipo de listado es necesario"); //muestra mensaje de error
					return; //termina
				}
				try {
					String selected = ComboWrapper.getSelectedKey(comboTipoListado);
					Collection<String> col = controlpac.getListado(selected);
					DefaultListModel<String> listModel = new DefaultListModel<String>(); //crea un list model
					for(String s: col){ //por cada informe
						listModel.addElement(s); //lo agrega al list
					}
					listado.setModel(listModel); //setea que la lista de especialidades sea el list model
					listado.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION); //setea que se pueda seleccionar uno solo
					scrollPaneListado.setViewportView(listado); //agrega scrollpane
				} catch (Exception e1) {
					HandleResponseUtil.showMessageError("Se ha producido un error");
				}
			}
		});
		btnBuscar.setBounds(291, 53, 96, 29);
		getContentPane().add(btnBuscar);
		
		JButton btnVolver = new JButton("Volver");//crea el boton volver
		btnVolver.setToolTipText("Presione este botón para volver al menú anterior");//crea el tooltip text
		btnVolver.setBounds(384, 300, 117, 29); //setea las medidas del boton
		btnVolver.addActionListener(new ActionListener() {//crea listener para escuchar clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				InformesView idw = new InformesView(); //crea la pantalla de informes
				idw.setVisible(true); //muestra la pantalla de ingreso de datos
				dispose();//cierra la pantalla de ingreso de datos del medico
			}
		});
		getContentPane().add(btnVolver);
		
		
		 scrollPaneListado = new JScrollPane(); //agrega scrollpane
		 scrollPaneListado.setBounds(77, 105, 391, 170); //setea dimensiones
			getContentPane().add(scrollPaneListado); //agrega scroll a ventana
			
			 listado = new JList<String>(); //agrega lista de especialidades
			 scrollPaneListado.setViewportView(listado); //setea el scroll
		
		
		
		JLabel lblListadoDePacientes = new JLabel("Listado de Pacientes y Medicos");
		lblListadoDePacientes.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblListadoDePacientes.setBounds(123, 16, 280, 16);
		getContentPane().add(lblListadoDePacientes);
		
		JLabel label = new JLabel("Usuario: "+UserProvider.getUsuarioLogueado());//crea un label
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 10));//setea la fuente del label
		label.setBounds(6, 17, 90 + label.getText().length(), 16);//setea las medidas del label
		getContentPane().add(label);//agrega el label a la ventana
		
		Map<String,String> map = new TreeMap<String,String>();
		map.put("1", "Medicos");
		map.put("2", "Pacientes");
		comboTipoListado = ComboWrapper.getCombo(map);
		comboTipoListado.setBounds(138, 54, 125, 27);
		getContentPane().add(comboTipoListado);
		
		comboTipoListado.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	DefaultListModel<String> listModel = new DefaultListModel<String>(); //crea un model vacio
				listado.setModel(listModel); //setea el model vacio
				scrollPaneListado.setViewportView(listado); //agrega scroll
	        }
	    });
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboTipoListado.setSelectedIndex(0);
				DefaultListModel<String> listModel = new DefaultListModel<String>(); //crea un model vacio
				listado.setModel(listModel); //setea el model vacio
				scrollPaneListado.setViewportView(listado); //agrega scroll
			}
		});
		btnLimpiar.setBounds(384, 53, 96, 29);
		getContentPane().add(btnLimpiar);
		
	}
}
