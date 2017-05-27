package ar.edu.unlam.analisis.pacientes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import ar.edu.unlam.analisis.enums.ETipoInforme;
import ar.edu.unlam.analisis.util.Encryptor;
import ar.edu.unlam.analisis.util.FileUtils;

public class controlpac {
	
	/*private static final String ARCHIVO_DATOS_MEDICO = "/Users/fpezzola/Desktop/datomed.txt";
	private static final String ARCHIVO_SITUACION_PACIENTE = "/Users/fpezzola/Desktop/situpac.txt";
	private static final String ARCHIVO_DATOS_PACIENTE = "/Users/fpezzola/Desktop/datopac.txt";*/
	private static final String ARCHIVO_DATOS_MEDICO = "C:\\datomed.txt";
	private static final String ARCHIVO_SITUACION_PACIENTE = "C:\\situpac.txt";
	private static final String ARCHIVO_DATOS_PACIENTE = "C:\\datopac.txt";
	
	@SuppressWarnings("unused")
	public static Collection<String> getInformeMedico(String codmed, ETipoInforme tipoInforme) throws Exception{
		Collection<String> colReturn = new ArrayList<String>();
		String archivo = ARCHIVO_DATOS_MEDICO;
		int sw;
		String codm="",nomm = "",espm;
			try {
				DataInputStream datomed = FileUtils.leerArchivo(archivo);

				sw = 1;
				
				while (sw != 0) {
					try {
						codm = datomed.readUTF();
						nomm = datomed.readUTF();
						espm = datomed.readUTF();

					} catch (EOFException e) {
						sw = 0;
					}

					if (codm.equals(codmed)) // compara el
												// codigo
												// extraido de
												// la
												// tabla
												// "datomed" con
												// el codigo
												// digitado
					{
						if(tipoInforme.equals(ETipoInforme.ESPECIALIDADES)){
							colReturn = darEspecialidadesMedico(nomm, codmed);
						}
						if(tipoInforme.equals(ETipoInforme.PACIENTES)){
							colReturn = darPacientesAtendidosPorMedico(nomm, codmed);
						}
					}
				}

			} catch (IOException ioe) {
			}
			return colReturn;
	}

	@SuppressWarnings({ "unused", "resource" })
	private static Collection<String> darEspecialidadesMedico(String nomm, String codtem)
			throws FileNotFoundException, IOException {
		Collection<String> colEspecialidades = new ArrayList<String>();
		int sw;
		String codp;
		String codme;
		String enfp;

		DataInputStream situpac = null;
		situpac = new DataInputStream(new FileInputStream(ARCHIVO_SITUACION_PACIENTE));

		sw = 1;
		while (sw != 0) {
			try {
				codp = situpac.readUTF();
				codme = situpac.readUTF();
				enfp = situpac.readUTF();

				if (codtem.equals(codme)) // compara
											// el
											// codigo
											// del
											// medico
											// de
											// la
											// tabla
											// "datomed"
											// con
											// el
											// codigo
											// del
											// medico
											// en
											// la
											// tabla
											// "situpac"

				{
					colEspecialidades.add(Encryptor.decode(enfp));
				}
			} catch (EOFException e) {
				sw = 0;
			}
		}
		return colEspecialidades;
	}
	
	@SuppressWarnings("unused")
	private static Collection<String> darPacientesAtendidosPorMedico(String nomm, String codtem)throws FileNotFoundException, IOException {
		Collection<String> colPacientes = new ArrayList<String>();
		DataInputStream situpac = FileUtils.leerArchivo(ARCHIVO_SITUACION_PACIENTE);
		String codp;
		String codpa;
		String nompa;
		String codme;
		String enfp;
		int sw=1;
		int sw1;
		while (sw != 0) {
			try {
				codp = situpac.readUTF();
				codme = situpac.readUTF();
				enfp = situpac.readUTF();

				if (codme.equals(codtem)) // compara
											// el
											// codigo
											// medico
											// de
											// la
											// tabla
											// "datomed"
											// con
											// el
											// de
											// la
											// tabla
											// "situpac"
				{

					DataInputStream datopac = FileUtils.leerArchivo(ARCHIVO_DATOS_PACIENTE);

					sw1 = 1;
					while (sw1 != 0) {
						try {
							codpa = datopac.readUTF();
							nompa = datopac.readUTF();

							if (codpa.equals(codp)) // compara
													// el
													// codigo
													// del
													// paciente
													// de
													// la
													// tabla
													// "situpac"
													// con
													// el
													// codigo
													// del
													// paciente
													// de
													// la
													// tabla
													// "datopac"
							{
								colPacientes.add(nompa);
							}
						} catch (EOFException e) {
							sw1 = 0;
						}
					}
				}
			} catch (EOFException e) {
				sw = 0;
			}
			
		}
		return colPacientes;
	}

	

	public static void nuevoMedico(String codmed, String nombre,String especializacion) throws FileNotFoundException{
		DataOutputStream datomed = null;
		datomed = new DataOutputStream(new FileOutputStream(ARCHIVO_DATOS_MEDICO));
		try {
			datomed.writeUTF(codmed);
				datomed.writeUTF(nombre);
				datomed.writeUTF(especializacion);
				datomed.close();

		} catch (IOException ioe) {
		}
	}
	
	public static void nuevaSituacionPaciente(String codpac, String codmed,String diagnostico) throws FileNotFoundException{
		DataOutputStream situpac = null;
		situpac = new DataOutputStream(new FileOutputStream(ARCHIVO_SITUACION_PACIENTE));
		try {
			situpac.writeUTF(codpac);
			situpac.writeUTF(codmed);
			situpac.writeUTF(Encryptor.encode(diagnostico));
			situpac.close();

		} catch (IOException ioe) {
		}
	}
	
	public static void nuevoPaciente(String codpac, String nompac) throws FileNotFoundException{
		DataOutputStream datopac = null;
		datopac = new DataOutputStream(new FileOutputStream(ARCHIVO_DATOS_PACIENTE));
		try {
				datopac.writeUTF(codpac);
				datopac.writeUTF(nompac);
				datopac.close();

		} catch (IOException ioe) {
		}
	}
	


}
