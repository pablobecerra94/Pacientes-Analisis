package ar.edu.unlam.analisis.validator;

public class TextValidator {
	
	public static String validarTexto(String text, int width, String nombreCampo){
		StringBuilder b = new StringBuilder(); //crea un builder de string
		if(text.length()>0){ //if
			if(!text.matches("^[a-zA-Z ]+$")){ //si no es letra o espacio
				b.append("*El ").append(nombreCampo).append(" debe contener solo letras y espacios").append("\n"); //mensajes
			}
			if(text.length()>width){ //if
				b.append("*El ").append(nombreCampo).append(" no puede superar los ").append(width).append(" caracteres"); //mensaje
			}
		}
		
		return b.toString(); //retorno
	}
	
	public static String validarNumero(String text, String nombreCampo){
		StringBuilder b = new StringBuilder(); //builder
		if(text.length()>0){ //if
		try{
			Long codigo = Long.parseLong(text); //parseo
				if(codigo<0){ //if
					b.append("*El ").append(nombreCampo).append(" debe ser mayor a 0.").append("\n"); //mensaje de error
				}
			}catch(NumberFormatException ex){
				b.append("*El ").append(nombreCampo).append(" no es numerico.").append("\n"); //mensaje de error
			}
		}
		
		return b.toString(); //mensaje
	}

}
