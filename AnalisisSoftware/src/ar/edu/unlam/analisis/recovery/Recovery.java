package ar.edu.unlam.analisis.recovery;

import java.io.File;

import ar.edu.unlam.analisis.util.LogUtils;

public class Recovery {
	
	public void recovery(IRecoveryStrategy strategy, String pathToFile) throws Exception{
		//check if files exits
		File f = new File(pathToFile);
		if(!f.exists()) { 
			f = new File(LogUtils.ARCHIVO_LOG);
			//hay log, entonces hago el recovery
			if(f.exists() && !f.isDirectory()){
				strategy.recovery();
			}
		}
	}

}
