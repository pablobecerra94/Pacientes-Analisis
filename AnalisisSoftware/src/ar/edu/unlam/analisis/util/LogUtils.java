package ar.edu.unlam.analisis.util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.analisis.enums.ETipoAccion;
import ar.edu.unlam.analisis.login.UserProvider;

public class LogUtils {
	
	public static final String ARCHIVO_LOG = "src//resources//log.lg";
	
	/**
	 * El metodo levanta el usuario logueado de un threadLocal implementado en el UserProvider
	 * Luego con el siguiente formato realiza el log:
	 * 
	 * usuariologueado:archDestino;accion:data:data...
	 * @param archDestino
	 * @param data
	 * @throws Exception
	 */
	
	public static void log(String archDestino,ETipoAccion accion, String... data)throws Exception{
		//Del threadLocal levantamos el usuario
		String usrLogueado = UserProvider.getUsuarioLogueado();
		StringBuilder sb = new StringBuilder(); //se crea un builder de strings
		
		//Sin usuario no podemos loguear
		if(usrLogueado == null){
			throw new Exception("No hay usuario logueado"); //lanza la excepcion de usuario no logueado
		}
		
		//No se enviaron datos para loguear
		if(data.length == 0){
			throw new Exception("No hay datos que loguear"); //lanza la excepcion de datos sin loguear 
		}
		
		sb.append(usrLogueado).append(":").append(archDestino).append(";").append(accion.getTextToLog()).append(";"); //agrega todos los datos
		
		for(String o : data){
			sb.append(o).append(":"); //agrega...
		}
		
		
		FileWriter fw = new FileWriter(new File(ARCHIVO_LOG),true); //crea el escritor archivo a partir del path
		BufferedWriter bw = new BufferedWriter(fw); //crea un escritor de buffer
		PrintWriter pw = new PrintWriter(bw); //crea un escritor de lineas de archivo
		pw.println(sb.toString().substring(0,sb.toString().length()-1));//usr:contraseña
		pw.close(); //cierra el escritor
	}
	
	public static List<List<String>>darDatosLog(ETipoAccion accion) throws Exception{
		List<List<String>> informe = new ArrayList<List<String>>(); //crea la lista de listas de string
		FileReader fr = new FileReader(new File(ARCHIVO_LOG));  //recupera el archivo de log
		BufferedReader br = new BufferedReader(fr); 	//crea el lector del archivo			
		String line;	//declaracion de string
		String accionDelArchivo; //declaracion de string

		while ((line = br.readLine()) != null) { 
			int index = line.indexOf(";");  //recupera el indice donde aparece ;
			String parte1 = line.substring(index+1);   //obtiene la subcadena desde el caracter 0 y el indice.
			List<String> datos = new ArrayList<String>(); //crea una lista de datos
			accionDelArchivo = parte1.substring(0,parte1.indexOf(";"));  //crea una subcadena desde cero hasta el indice ; 
			if(accionDelArchivo.equalsIgnoreCase(accion.getTextToLog())){ //pregunta si la accion del archivo es la misma del log
				String parteDatos = parte1.substring(parte1.indexOf(";")+1); //crea otra subcadena
				String[]split = parteDatos.split(":"); //la separa por :
				for(String d: split){ //recorre el vector de string
					datos.add(d); //agrega el dato
				}
				informe.add(datos); //agrega los datos al informe
			}
		}
		br.close(); //cierra el archivo
		return informe; //retorna el informe 
	}
}
