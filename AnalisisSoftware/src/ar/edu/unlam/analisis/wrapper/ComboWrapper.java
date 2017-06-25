package ar.edu.unlam.analisis.wrapper;

import java.util.Map;

import javax.swing.JComboBox;

import ar.edu.unlam.analisis.combo.ComboItem;

public class ComboWrapper {
	
	private static void getSeleccionar(JComboBox<ComboItem> combo){
		combo.addItem(new ComboItem("-1", "Seleccionar")); //seleccionar
		combo.addItem(new ComboItem("-2", "-----------")); //linea
		setDefaultSelected(combo); //default
	}
	
	public static JComboBox<ComboItem> getComboVacio(){
		JComboBox<ComboItem> combo = new JComboBox<ComboItem>(); //seleccionar
		getSeleccionar(combo); //linea
		return combo; //default
	}
	
	
	public static JComboBox<ComboItem> getCombo(Map<String,String> map){
		JComboBox<ComboItem> combo = new JComboBox<ComboItem>(); //combito
		getSeleccionar(combo); //seleccionar
		for(Map.Entry<String, String>entry : map.entrySet()){ //for
			combo.addItem(new ComboItem(entry.getKey(), entry.getValue())); //agregado de combo
		}
		return combo; //retorno
	}
	
	public static boolean isSeleccionado(String value){
		return !value.equalsIgnoreCase("-1")?!value.equalsIgnoreCase("-2")?true:false:false; //si esta seleccionado
	}
	
	public static void setDefaultSelected(JComboBox<ComboItem>combo){ //
		combo.setSelectedIndex(0); //seleccionador defecto
	} //
	
	public static String getSelectedKey(JComboBox<ComboItem>combo){ //
		return ((ComboItem)combo.getSelectedItem()).getKey(); // obtener seleccionado
	}
	
	public static String getSelectedValue(JComboBox<ComboItem>combo){ //
		return ((ComboItem)combo.getSelectedItem()).getDescripcion(); //descripcion seleccionada
	}

}
