package ar.edu.unlam.analisis.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ar.edu.unlam.analisis.login.UserProvider;

@SuppressWarnings("serial")
public class IngresoDatosView extends JFrame{
	public IngresoDatosView() {
		getContentPane().setLayout(null);//setea un layout absoluto
		setSize(new Dimension(500, 500));//setea la dimension de la ventana
		setLocationRelativeTo(null);//setea que no este relativa a nada la pantalla
		JLabel lblIngresoDePacientes = new JLabel("Ingreso de Pacientes");//crea un label
		lblIngresoDePacientes.setBounds(158, 18, 135, 16);//setea las medidas
		getContentPane().add(lblIngresoDePacientes);//lo agrega a la pantalla
		
		JButton btnDatosPaciente = new JButton("Datos Paciente");//crea un boton
		btnDatosPaciente.setToolTipText("Presione este botón para ingresar datos de un nuevo paciente");//agrega el tooltip text
		btnDatosPaciente.addActionListener(new ActionListener() {//agrega un listener para escuchar los clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				DatosPacienteView dp = new DatosPacienteView(); // crea un DatosPacientesView
				dp.setVisible(true); //muestra un DatosPacientesView
				dispose();//cierra la pantalla
			}
		});
		btnDatosPaciente.setBounds(158, 73, 152, 29);//setea las medidas
		getContentPane().add(btnDatosPaciente);//lo agrega a la pantalla
		
		JButton btnSituacionPaciente = new JButton("Situación Paciente");//crea un boton
		btnSituacionPaciente.setToolTipText("Presione este botón para ingresar una nueva situación de un paciente atendida por un médico");//agrega el tooltip text
		btnSituacionPaciente.addActionListener(new ActionListener() {//agrega un listener para escuchar los clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				SituacionPacienteView spw = new SituacionPacienteView();//crea un SituacionPacienteView
				spw.setVisible(true);//muestra un SituacionPacienteView
				dispose(); //cierra la pantalla
			}
		});
		btnSituacionPaciente.setBounds(158, 114, 152, 29);//setea las medidas
		getContentPane().add(btnSituacionPaciente);//lo agrega a la pantalla
		
		JButton btnDatosMedico = new JButton("Datos Medico");//crea un boton
		btnDatosMedico.setToolTipText("Presione este botón para ingresar datos de un nuevo médico");//agrega el tooltip text
		btnDatosMedico.addActionListener(new ActionListener() {//agrega un listener para escuchar los clicks
			public void actionPerformed(ActionEvent e) {//metodo para escuchar clicks
				DatosMedicoView dmw = new DatosMedicoView(); //crea una DatosMedicoView
				dmw.setVisible(true);//muestra un DatosMedicoView
				setVisible(false);
				dispose();//cierra la pantalla
			}
		});
		btnDatosMedico.setBounds(158, 155, 152, 29);//setea las medidas
		getContentPane().add(btnDatosMedico);//lo agrega a la pantalla
		
		JButton btnVolver = new JButton("Volver");//crea un boton
		btnVolver.setToolTipText("Presione este botón para volver al menú anterior");//agrega el tooltip text
		btnVolver.addActionListener(new ActionListener() {//agrega un listener para escuchar los clicks
			public void actionPerformed(ActionEvent e) {
				MainView mw = new MainView(); //crea un MainView
				mw.setVisible(true);//muestra un MainView
				dispose();//cierra la pantalla
			}
		});
		btnVolver.setBounds(327, 243, 117, 29);//setea las medidas
		getContentPane().add(btnVolver);//lo agrega a la pantalla
		
		JLabel label = new JLabel("Usuario: "+UserProvider.getUsuarioLogueado());//crea un label
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 10));//setea la fuente del label
		label.setBounds(6, 17, 90 + label.getText().length(), 16);//setea las medidas
		getContentPane().add(label);//lo agrega a la pantalla
	}

}
