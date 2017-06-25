package ar.edu.unlam.analisis.combo;

public class ComboItem {
	
	private String key;
	private String descripcion;
	
	public ComboItem(String key, String descripcion) {
		this.key = key;
		this.descripcion = descripcion;
	}
	
	
	
	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	@Override
	public String toString() {
		return this.descripcion;
	}

}
