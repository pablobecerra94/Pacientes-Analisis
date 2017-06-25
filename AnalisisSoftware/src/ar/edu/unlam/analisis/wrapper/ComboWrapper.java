package ar.edu.unlam.analisis.wrapper;

import java.util.Map;

import javax.swing.JComboBox;

import ar.edu.unlam.analisis.combo.ComboItem;

public class ComboWrapper {
	
	private static void getSeleccionar(JComboBox<ComboItem> combo){
		combo.addItem(new ComboItem("-1", "Seleccionar"));
		combo.addItem(new ComboItem("-2", "-----------"));
		setDefaultSelected(combo);
	}
	
	public static JComboBox<ComboItem> getComboVacio(){
		JComboBox<ComboItem> combo = new JComboBox<ComboItem>();
		getSeleccionar(combo);
		return combo;
	}
	
	
	public static JComboBox<ComboItem> getCombo(Map<String,String> map){
		JComboBox<ComboItem> combo = new JComboBox<ComboItem>();
		getSeleccionar(combo);
		for(Map.Entry<String, String>entry : map.entrySet()){
			combo.addItem(new ComboItem(entry.getKey(), entry.getValue()));
		}
		return combo;
	}
	
	public static boolean isSeleccionado(String value){
		return !value.equalsIgnoreCase("-1")?!value.equalsIgnoreCase("-2")?true:false:false;
	}
	
	public static void setDefaultSelected(JComboBox<ComboItem>combo){
		combo.setSelectedIndex(0);
	}
	
	public static String getSelectedKey(JComboBox<ComboItem>combo){
		return ((ComboItem)combo.getSelectedItem()).getKey();
	}
	
	public static String getSelectedValue(JComboBox<ComboItem>combo){
		return ((ComboItem)combo.getSelectedItem()).getDescripcion();
	}

}
