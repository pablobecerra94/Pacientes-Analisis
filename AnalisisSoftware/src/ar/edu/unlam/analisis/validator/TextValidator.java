package ar.edu.unlam.analisis.validator;

public class TextValidator {
	
	public static String validarTexto(String text, int width, String nombreCampo){
		StringBuilder b = new StringBuilder();
		if(text.length()>0){
			if(!text.matches("^[a-zA-Z]+$")){
				b.append("*El ").append(nombreCampo).append(" debe contener solo letras").append("\n");
			}
			if(text.length()>width){
				b.append("*El ").append(nombreCampo).append(" no puede superar los ").append(width).append(" caracteres");
			}
		}
		
		return b.toString();
	}
	
	public static String validarNumero(String text, String nombreCampo){
		StringBuilder b = new StringBuilder();
		if(text.length()>0){
		try{
			Long codigo = Long.parseLong(text);
				if(codigo<0){
					b.append("*El ").append(nombreCampo).append(" debe ser mayor a 0.").append("\n");
				}
			}catch(NumberFormatException ex){
				b.append("*El ").append(nombreCampo).append(" no es numerico.").append("\n");
			}
		}
		
		return b.toString();
	}

}
