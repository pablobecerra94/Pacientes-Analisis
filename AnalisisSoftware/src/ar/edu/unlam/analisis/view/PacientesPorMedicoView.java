package ar.edu.unlam.analisis.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ar.edu.unlam.analisis.enums.ETipoInforme;
import ar.edu.unlam.analisis.login.UserProvider;
import ar.edu.unlam.analisis.pacientes.controlpac;
import ar.edu.unlam.analisis.util.HandleResponseUtil;

@SuppressWarnings("serial")
public class PacientesPorMedicoView extends JFrame{
	private JTextField textFieldCodigoMedico;
	private JList<String> listPacientes;
	private JScrollPane scrollPanePacientes;
	public PacientesPorMedicoView() {
		getContentPane().setLayout(null);
		setSize(new Dimension(500, 500));
		setLocationRelativeTo(null);
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformesView iw = new InformesView();
				iw.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(357, 294, 117, 29);
		getContentPane().add(btnVolver);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldCodigoMedico.getText().isEmpty()){
					HandleResponseUtil.showMessageError("El codigo del meedico es necesario");
					return;
				}
				
				try {
					Collection<String> col = controlpac.getInformeMedico(textFieldCodigoMedico.getText(), ETipoInforme.PACIENTES);
					DefaultListModel listModel = new DefaultListModel();
					for(String s: col){
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
		btnBuscar.setBounds(273, 49, 117, 29);
		getContentPane().add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldCodigoMedico.setText("");
				DefaultListModel listModel = new DefaultListModel();
				listPacientes.setModel(listModel);
				scrollPanePacientes.setViewportView(listPacientes);
			}
		});
		btnLimpiar.setBounds(383, 49, 117, 29);
		getContentPane().add(btnLimpiar);
		
		textFieldCodigoMedico = new JTextField();
		textFieldCodigoMedico.setBounds(127, 49, 130, 26);
		getContentPane().add(textFieldCodigoMedico);
		textFieldCodigoMedico.setColumns(10);
		
		JLabel lblCodigoMedico = new JLabel("Codigo Medico:");
		lblCodigoMedico.setBounds(6, 54, 125, 16);
		getContentPane().add(lblCodigoMedico);
		
		 scrollPanePacientes = new JScrollPane();
		scrollPanePacientes.setBounds(91, 108, 299, 158);
		getContentPane().add(scrollPanePacientes);
		
		listPacientes = new JList<String>();
		scrollPanePacientes.setViewportView(listPacientes);
		
		JLabel lblPacientesPorMedico = new JLabel("Pacientes por Medico");
		lblPacientesPorMedico.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPacientesPorMedico.setBounds(171, 21, 246, 16);
		getContentPane().add(lblPacientesPorMedico);
		JLabel label = new JLabel("Usuario: "+UserProvider.getUsuarioLogueado());
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		label.setBounds(6, 17, 90 + label.getText().length(), 16);
		getContentPane().add(label);
	}
}
