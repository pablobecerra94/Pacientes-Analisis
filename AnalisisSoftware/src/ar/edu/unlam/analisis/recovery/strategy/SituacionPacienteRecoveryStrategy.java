package ar.edu.unlam.analisis.recovery.strategy;

import java.util.List;

import ar.edu.unlam.analisis.enums.ETipoAccion;
import ar.edu.unlam.analisis.pacientes.controlpac;
import ar.edu.unlam.analisis.recovery.IRecoveryStrategy;
import ar.edu.unlam.analisis.util.Encryptor;
import ar.edu.unlam.analisis.util.LogUtils;

public class SituacionPacienteRecoveryStrategy implements IRecoveryStrategy{

	@Override
	public void recovery() throws Exception {
		List<List<String>> datosLog = LogUtils.darDatosLog(ETipoAccion.ALTA_SITUACION_PACIENTE);
		for(List<String> datos : datosLog){
			controlpac.nuevaSituacionPaciente(datos.get(0), datos.get(1), Encryptor.decode(datos.get(2)), false);
		}
	}

}
