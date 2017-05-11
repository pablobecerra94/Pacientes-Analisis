package ar.edu.unlam.analisis.view;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import ar.edu.unlam.analisis.enums.ETipoInforme;
import ar.edu.unlam.analisis.pacientes.controlpac;
import ar.edu.unlam.analisis.util.HandleResponseUtil;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Font;

@SuppressWarnings("serial")
public class EnfermedadesMedicoView extends JFrame{
	private JTextField textFieldCodMedico;
	private JList<String> listEspecialidades;
	private JScrollPane scrollPaneEspecialidades;
	public EnfermedadesMedicoView() {
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
		btnVolver.setBounds(361, 291, 117, 29);
		getContentPane().add(btnVolver);
		
		JLabel lblCodigoMedico = new JLabel("Codigo Medico:");
		lblCodigoMedico.setBounds(1, 65, 105, 16);
		getContentPane().add(lblCodigoMedico);
		
		textFieldCodMedico = new JTextField();
		textFieldCodMedico.setBounds(118, 60, 130, 26);
		getContentPane().add(textFieldCodMedico);
		textFieldCodMedico.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldCodMedico.getText().isEmpty()){
					HandleResponseUtil.showMessageError("El codigo del meedico es necesario");
					return;
				}
				
				try {
					Collection<String> col = controlpac.getInformeMedico(textFieldCodMedico.getText(), ETipoInforme.ESPECIALIDADES);
					DefaultListModel listModel = new DefaultListModel();
					for(String s: col){
						listModel.addElement(s);
					}
					listEspecialidades.setModel(listModel);
					listEspecialidades.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
					scrollPaneEspecialidades.setViewportView(listEspecialidades);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(260, 60, 117, 29);
		getContentPane().add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldCodMedico.setText("");
				DefaultListModel listModel = new DefaultListModel();
				listEspecialidades.setModel(listModel);
				scrollPaneEspecialidades.setViewportView(listEspecialidades);
			}
		});
		btnLimpiar.setBounds(377, 60, 117, 29);
		getContentPane().add(btnLimpiar);
		
		 scrollPaneEspecialidades = new JScrollPane();
		scrollPaneEspecialidades.setBounds(77, 105, 244, 162);
		getContentPane().add(scrollPaneEspecialidades);
		
		 listEspecialidades = new JList<String>();
		scrollPaneEspecialidades.setViewportView(listEspecialidades);
		
		JLabel lblEspecialidadesDelMedico = new JLabel("Especialidades del Medico");
		lblEspecialidadesDelMedico.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblEspecialidadesDelMedico.setBounds(118, 19, 238, 16);
		getContentPane().add(lblEspecialidadesDelMedico);
	}
}
