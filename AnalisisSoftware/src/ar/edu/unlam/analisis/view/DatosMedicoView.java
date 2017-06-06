package ar.edu.unlam.analisis.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ar.edu.unlam.analisis.login.UserProvider;
import ar.edu.unlam.analisis.pacientes.controlpac;
import ar.edu.unlam.analisis.util.HandleResponseUtil;

@SuppressWarnings("serial")
public class DatosMedicoView extends JFrame{
	private JTextField textCodigo;
	private JTextField textNombre;
	private JTextField textEspecializacion;
	public DatosMedicoView() {
		getContentPane().setLayout(null);
		setSize(new Dimension(500, 500));
		setLocationRelativeTo(null);
		JLabel lblNuevoMedico = new JLabel("Nuevo Medico");
		lblNuevoMedico.setBounds(160, 6, 100, 16);
		getContentPane().add(lblNuevoMedico);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(140, 34, 130, 26);
		getContentPane().add(textCodigo);
		textCodigo.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(140, 65, 130, 26);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		textEspecializacion = new JTextField();
		textEspecializacion.setBounds(140, 103, 130, 26);
		getContentPane().add(textEspecializacion);
		textEspecializacion.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(37, 39, 61, 16);
		getContentPane().add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(37, 70, 61, 16);
		getContentPane().add(lblNombre);
		
		JLabel lblEspecializacion = new JLabel("Especializacion:");
		lblEspecializacion.setBounds(37, 108, 102, 16);
		getContentPane().add(lblEspecializacion);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setBounds(70, 154, 117, 29);
		getContentPane().add(btnLimpiar);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder errors = new StringBuilder("");
				if(textCodigo.getText().isEmpty()){
					errors.append("*El codigo es necesario.").append("\n");
				}
				if(textEspecializacion.getText().isEmpty()){
					errors.append("*La especializacion es necesaria.").append("\n");
				}
				if(textNombre.getText().isEmpty()){
					errors.append("*El nombre es necesario.").append("\n");
				}
				
				if(errors.toString().isEmpty()){
					try {
						controlpac.nuevoMedico(textCodigo.getText(), textNombre.getText(), textEspecializacion.getText());
						HandleResponseUtil.showMessageSuccess(HandleResponseUtil.COMMON_SUCCESS);
						limpiar();
					} catch (Exception e1) {
						HandleResponseUtil.showMessageError(HandleResponseUtil.COMMON_ERROR);
					}
				}else{
					HandleResponseUtil.showMessageError(errors.toString());
				}
			}
		});
		btnRegistrar.setBounds(217, 154, 117, 29);
		getContentPane().add(btnRegistrar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresoDatosView idw = new IngresoDatosView();
				idw.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(327, 243, 117, 29);
		getContentPane().add(btnVolver);
		JLabel label = new JLabel("Usuario: "+UserProvider.getUsuarioLogueado());
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		label.setBounds(6, 17, 90 + label.getText().length(), 16);
		getContentPane().add(label);
	}
	
	public void limpiar(){
		textCodigo.setText("");
		textEspecializacion.setText("");
		textNombre.setText("");
	}

}
