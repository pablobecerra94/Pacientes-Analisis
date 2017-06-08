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
		r.recovery(new MedicoRecoveryStrategy(), controlpac.ARCHIVO_DATOS_MEDICO); //Recupera el archivo de datos de medicos
		r.recovery(new PacienteRecoveryStrategy(), controlpac.ARCHIVO_DATOS_PACIENTE); //Recupera el archivo de datos de pacientes
		r.recovery(new SituacionPacienteRecoveryStrategy(), controlpac.ARCHIVO_SITUACION_PACIENTE); //Recupera el archivo de situaciones de pacientes
		LoginView lw = new LoginView(); //Crea la vista de login
		lw.setVisible(true); //muestra la vista de login
	}

}
