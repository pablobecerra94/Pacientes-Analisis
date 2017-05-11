package ar.edu.unlam.analisis.util;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HandleResponseUtil {
	
	public static final String COMMON_ERROR = "Ocurrio un error, por favor intente nuevamente.";
	public static final String COMMON_SUCCESS = "Operacion realizada con exito";
	
	public static void showMessageError(String msj){
		JOptionPane.showMessageDialog(new JPanel(), msj, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showMessageSuccess(String msj){
		JOptionPane.showMessageDialog(new JPanel(), msj, "Exito", JOptionPane.INFORMATION_MESSAGE);
	}
}
