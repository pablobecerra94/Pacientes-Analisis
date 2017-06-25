package ar.edu.unlam.analisis.view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ar.edu.unlam.analisis.login.UserProvider;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class InformesView extends JFrame{
	
	public InformesView() {
		getContentPane().setLayout(null); //setea layout absoluto
		setSize(new Dimension(500, 500)); //setea dimensiones
		setLocationRelativeTo(null); //relativo a nada
		JLabel lblControlDePacientes = new JLabel("Control de Pacientes"); //crea label
		lblControlDePacientes.setFont(new Font("Lucida Grande", Font.PLAIN, 16)); //setea fuente
		lblControlDePacientes.setBounds(132, 17, 185, 16); //setea dimensiones
		getContentPane().add(lblControlDePacientes); //agrega label a ventana
		
		JLabel lblInformes = new JLabel("Informes"); //crea label
		lblInformes.setBounds(186, 45, 61, 16); //setea dimensiones
		getContentPane().add(lblInformes); //agrega label a la ventana
		
		JButton btnListadoDePacientes = new JButton("Listado de Pacientes Por Medico"); //crea boton
		btnListadoDePacientes.setToolTipText("Presione este boton para obtener los pacientes que son atendidos por un médico ingresado"); //setea tooltip
		btnListadoDePacientes.addActionListener(new ActionListener() { //listener para clicks
			public void actionPerformed(ActionEvent e) { //metodo para clicks
				PacientesPorMedicoView ppmw = new PacientesPorMedicoView(); //crea vista para ver pacientes por medico
				ppmw.setVisible(true); //muestra la vista
				dispose(); //Cierra la actual
			}
		});
		btnListadoDePacientes.setBounds(71, 86, 300, 29); //setea dimensiones
		getContentPane().add(btnListadoDePacientes); //agrega boton a ventana
		
		JButton btnEnfrermedadesQueAtiende = new JButton("Enfermedades que atiende cada Medico"); //crea boton
		btnEnfrermedadesQueAtiende.setToolTipText("Presione este boton para obtener las enfermedades tratadas por un médico ingresado"); //setea tooltip
		btnEnfrermedadesQueAtiende.addActionListener(new ActionListener() { //listener para clicks
			public void actionPerformed(ActionEvent e) { //metodo para clicks
				EnfermedadesMedicoView emw = new EnfermedadesMedicoView(); //crea vista para ver enfermedades por medico
				emw.setVisible(true); ///muestra la vista
				dispose();  //cierra ventana actual
			}
		});
		btnEnfrermedadesQueAtiende.setBounds(71, 127, 300, 29);  //setea dimensiones
		getContentPane().add(btnEnfrermedadesQueAtiende); //agrega boton a ventana
		
		JButton btnVolver = new JButton("Volver"); //crea boton
		btnVolver.setToolTipText("Presione este botón para volver al menú anterior"); //setea tooltip
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView mw = new MainView(); //crea vista para menu principal
				mw.setVisible(true); //muestra la vista
				dispose(); //cierra ventana actual
			}
		});
		btnVolver.setBounds(327, 243, 117, 29); //setea dimensiones
		getContentPane().add(btnVolver); //agrega boton a ventana
		
		JLabel label = new JLabel("Usuario: "+UserProvider.getUsuarioLogueado()); //crea label
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 10)); //setea la fuente
		label.setBounds(6, 17, 90 + label.getText().length(), 16); //setea dimensiones
		getContentPane().add(label); //agrega label
		
		JButton btnListadoDePacientes_1 = new JButton("Listado de Pacientes y Medicos");
		btnListadoDePacientes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoView lw = new ListadoView();
				lw.setVisible(true);
				dispose();
			}
		});
		btnListadoDePacientes_1.setBounds(71, 168, 300, 29);
		getContentPane().add(btnListadoDePacientes_1);
	}

}
