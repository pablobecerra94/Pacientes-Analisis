package ar.edu.unlam.analisis.main;

import ar.edu.unlam.analisis.pacientes.controlpac;
import ar.edu.unlam.analisis.recovery.Recovery;
import ar.edu.unlam.analisis.recovery.strategy.MedicoRecoveryStrategy;
import ar.edu.unlam.analisis.recovery.strategy.PacienteRecoveryStrategy;
import ar.edu.unlam.analisis.recovery.strategy.SituacionPacienteRecoveryStrategy;
import ar.edu.unlam.analisis.view.LoginView;

public class Main {
	
	public static void main(String args[]) throws Exception {
		Recovery r = new Recovery();
		r.recovery(new MedicoRecoveryStrategy(), controlpac.ARCHIVO_DATOS_MEDICO);
		r.recovery(new PacienteRecoveryStrategy(), controlpac.ARCHIVO_DATOS_PACIENTE);
		r.recovery(new SituacionPacienteRecoveryStrategy(), controlpac.ARCHIVO_SITUACION_PACIENTE);
		LoginView lw = new LoginView();
		lw.setVisible(true);
	}

}
