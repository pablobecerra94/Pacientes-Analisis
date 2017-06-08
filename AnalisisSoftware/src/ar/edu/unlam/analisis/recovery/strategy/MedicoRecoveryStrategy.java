package ar.edu.unlam.analisis.recovery.strategy;


import java.util.List;

import ar.edu.unlam.analisis.enums.ETipoAccion;
import ar.edu.unlam.analisis.pacientes.controlpac;
import ar.edu.unlam.analisis.recovery.IRecoveryStrategy;
import ar.edu.unlam.analisis.util.LogUtils;

public class MedicoRecoveryStrategy implements IRecoveryStrategy{

	@Override
	public void recovery() throws Exception {
		List<List<String>> datosLog = LogUtils.darDatosLog(ETipoAccion.ALTA_MEDICO);
		for(List<String> datos : datosLog){
			controlpac.nuevoMedico(datos.get(0), datos.get(1), datos.get(2), false);
		}
	}

}
