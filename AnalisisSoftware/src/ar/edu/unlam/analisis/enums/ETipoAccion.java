package ar.edu.unlam.analisis.enums;

public enum ETipoAccion {
	
	ALTA_PACIENTE("altaPaciente"),
	ALTA_MEDICO("altaMedico"),
	ALTA_SITUACION_PACIENTE("altaSituacionPaciente");
	
	String textToLog;
	
	private ETipoAccion(String textToLog) {
		this.textToLog = textToLog;
	}

	public String getTextToLog() {
		return textToLog;
	}

	public void setTextToLog(String textToLog) {
		this.textToLog = textToLog;
	}
	
	

}
