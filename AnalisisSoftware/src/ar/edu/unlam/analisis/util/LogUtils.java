package ar.edu.unlam.analisis.util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
		StringBuilder sb = new StringBuilder();
		
		//Sin usuario no podemos loguear
		if(usrLogueado == null){
			throw new Exception("No hay usuario logueado");
		}
		
		//No se enviaron datos para loguear
		if(data.length == 0){
			throw new Exception("No hay datos que loguear");
		}
		
		sb.append(usrLogueado).append(":").append(archDestino).append(";").append(accion.getTextToLog()).append(";");
		
		for(String o : data){
			sb.append(o).append(":");
		}
		
		
		FileWriter fw = new FileWriter(new File(ARCHIVO_LOG),true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		pw.println(sb.toString().substring(0,sb.toString().length()-1));//usr:contraseña
		pw.close();
	}
	
	public static List<List<String>>darDatosLog(ETipoAccion accion) throws Exception{
		List<List<String>> informe = new ArrayList<List<String>>();
		FileReader fr = new FileReader(new File(ARCHIVO_LOG)); 
		BufferedReader br = new BufferedReader(fr); 				
		String line;
		String accionDelArchivo;

		while ((line = br.readLine()) != null) { 
			int index = line.indexOf(";");
			String parte1 = line.substring(index+1);
			List<String> datos = new ArrayList<String>();
			accionDelArchivo = parte1.substring(0,parte1.indexOf(";")); 
			if(accionDelArchivo.equalsIgnoreCase(accion.getTextToLog())){
				String parteDatos = parte1.substring(parte1.indexOf(";")+1);
				String[]split = parteDatos.split(":");
				for(String d: split){
					datos.add(d);
				}
				informe.add(datos);
			}
		}
		br.close();
		return informe;
	}
}
