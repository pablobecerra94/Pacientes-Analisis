package ar.edu.unlam.analisis.recovery;

import java.io.File;

import ar.edu.unlam.analisis.util.LogUtils;

public class Recovery {
	
	public void recovery(IRecoveryStrategy strategy, String pathToFile) throws Exception{
		//check if files exits
		File f = new File(pathToFile); //crea el objeto archivo desde el path
		if(!f.exists()) {  //si no existe
			f = new File(LogUtils.ARCHIVO_LOG); //se abre el archivo de log
			//hay log, entonces hago el recovery
			if(f.exists() && !f.isDirectory()){ //si el archivo de log existe y no es un directorio
				strategy.recovery(); //se recupera el archivo
			}
		}
	}

}
