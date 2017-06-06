package ar.edu.unlam.analisis.pacientes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ar.edu.unlam.analisis.enums.ETipoAccion;
import ar.edu.unlam.analisis.enums.ETipoInforme;
import ar.edu.unlam.analisis.util.Encryptor;
import ar.edu.unlam.analisis.util.FileUtils;
import ar.edu.unlam.analisis.util.LogUtils;

public class controlpac {

	private static final String ARCHIVO_DATOS_MEDICO = "src//resources//datomed.pac";
	private static final String ARCHIVO_SITUACION_PACIENTE = "src//resources//situpac.pac";
	private static final String ARCHIVO_DATOS_PACIENTE = "src//resources//datopac.pac";

	@SuppressWarnings("unused")
	public static Collection<String> getInformeMedico(String codmed, ETipoInforme tipoInforme) throws Exception {
		Collection<String> colReturn = new ArrayList<String>();
		String archivo = ARCHIVO_DATOS_MEDICO;
		int sw;
		String codm = "", nomm = "", espm;
		try {
			DataInputStream datomed = FileUtils.leerArchivo(archivo);
			List<String> archivoString = Files.readAllLines(Paths.get(ARCHIVO_DATOS_MEDICO));
			sw = 1;
			String[] archivoPartido = archivoString.get(0).split("");
			/*
			 * while (sw != 0) { try {
			 * String[]archivoPartido=archivoString.get(0).split(" ");
			 * System.out.println(archivoPartido[0]); codm = datomed.readUTF();
			 * nomm = datomed.readUTF(); espm = datomed.readUTF();
			 * 
			 * } catch (EOFException e) { e.printStackTrace(); sw = 0; }
			 */

			for (int i = 0; i < archivoPartido.length; i++) {
				codm = archivoPartido[i++];
				nomm = archivoPartido[i++];
				espm = archivoPartido[i++];

				if (codm.equals(codmed)) // compara el codigo extraido de la
											// tabla "datomed" con el codigo
											// digitado
				{
					if (tipoInforme.equals(ETipoInforme.ESPECIALIDADES)) {
						colReturn = darEspecialidadesMedico(nomm, codmed);
					}
					if (tipoInforme.equals(ETipoInforme.PACIENTES)) {
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
	private static Collection<String> darPacientesAtendidosPorMedico(String nomm, String codtem)
			throws FileNotFoundException, IOException {
		Collection<String> colPacientes = new ArrayList<String>();
		DataInputStream situpac = FileUtils.leerArchivo(ARCHIVO_SITUACION_PACIENTE);
		String codp;
		String codpa;
		String nompa;
		String codme;
		String enfp;
		int sw = 1;
		int sw1;
		while (sw != 0) {
			try {
				codp = situpac.readUTF();
				codme = situpac.readUTF();
				enfp = situpac.readUTF();

				if (codme.equals(codtem)) // compara el codigo medico de la
											// tabla "datomed" con el de la
											// tabla "situpac"
				{

					DataInputStream datopac = FileUtils.leerArchivo(ARCHIVO_DATOS_PACIENTE);

					sw1 = 1;
					while (sw1 != 0) {
						try {
							codpa = datopac.readUTF();
							nompa = datopac.readUTF();

							if (codpa.equals(codp)) // compara el codigo del
													// paciente de la tabla
													// "situpac" con el codigo
													// del paciente de la tabla
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

	public static void nuevoMedico(String codmed, String nombre, String especializacion) throws Exception {

		DataOutputStream datomed = null;
		datomed = new DataOutputStream(new FileOutputStream(ARCHIVO_DATOS_MEDICO, true));
		try {

			datomed.writeUTF(codmed);
			datomed.writeUTF(nombre);
			datomed.writeUTF(especializacion);
			datomed.flush();
			datomed.close();
			LogUtils.log(ARCHIVO_DATOS_MEDICO, ETipoAccion.ALTA_MEDICO, codmed, nombre, especializacion);

		} catch (Exception ioe) {
			throw new Exception(ioe.getMessage());
		}
		/*
		 * PrintWriter printWriter = new PrintWriter(new FileOutputStream(new
		 * File(ARCHIVO_DATOS_MEDICO), true)); printWriter.print((codmed + " " +
		 * nombre + " " + especializacion + " ")); printWriter.close();
		 */

	}

	public static void nuevaSituacionPaciente(String codpac, String codmed, String diagnostico) throws Exception {
		/*
		 * DataOutputStream situpac = null; situpac = new DataOutputStream(new
		 * FileOutputStream(ARCHIVO_SITUACION_PACIENTE, true)); try {
		 * situpac.writeUTF(codpac); situpac.writeUTF(codmed);
		 * situpac.writeUTF(Encryptor.encode(diagnostico)); situpac.close();
		 * LogUtils.log(ARCHIVO_SITUACION_PACIENTE,
		 * ETipoAccion.ALTA_SITUACION_PACIENTE, codpac, codmed,
		 * Encryptor.encode(diagnostico));
		 * 
		 * } catch (Exception ioe) { throw new Exception(ioe.getMessage()); }
		 */

		/*
		 * PrintWriter printWriter = new PrintWriter(new FileOutputStream(new
		 * File(ARCHIVO_SITUACION_PACIENTE), true)); printWriter.print((codpac +
		 * " " + codmed + " " + Encryptor.encode(diagnostico) + " "));
		 * printWriter.close();
		 */

	}

	public static void nuevoPaciente(String codpac, String nompac) throws Exception {
		DataOutputStream datopac = null;
		datopac = new DataOutputStream(new FileOutputStream(ARCHIVO_DATOS_PACIENTE, true));
		try {
			datopac.writeUTF(codpac);
			datopac.writeUTF(nompac);
			datopac.close();
			LogUtils.log(ARCHIVO_SITUACION_PACIENTE, ETipoAccion.ALTA_SITUACION_PACIENTE, codpac, nompac);

		} catch (Exception ioe) {
			throw new Exception(ioe.getMessage());
		}

		/*
		 * PrintWriter printWriter = new PrintWriter(new FileOutputStream(new
		 * File(ARCHIVO_DATOS_PACIENTE), true)); printWriter.print((codpac + " "
		 * + nompac + " ")); printWriter.close();
		 */

	}

}
