package ar.edu.unlam.analisis.pacientes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import ar.edu.unlam.analisis.enums.ETipoInforme;
import ar.edu.unlam.analisis.util.Encryptor;
import ar.edu.unlam.analisis.util.FileUtils;

public class controlpac {

	private static final String ARCHIVO_DATOS_MEDICO = "src//resources//datomed.pac";
	private static final String ARCHIVO_SITUACION_PACIENTE = "src//resources//situpac.txt";
	private static final String ARCHIVO_DATOS_PACIENTE = "src//resources//datopac.txt";

	@SuppressWarnings("unused")
	public static Collection<String> getInformeMedico(String codmed, ETipoInforme tipoInforme) throws Exception {
		Collection<String> colReturn = new ArrayList<String>();
		int sw;
		String codm = "", nomm = "", espm;
		try {
			FileReader fr = new FileReader(new File(ARCHIVO_DATOS_MEDICO));
			BufferedReader br = new BufferedReader(fr);
			String line;

			while ((line = br.readLine()) != null) {
				int index = line.indexOf(":");
				codm = line.substring(0, index);
				int index2 = line.indexOf("|");
				nomm = line.substring(index + 1, index2);
				espm = line.substring(index2 + 1, line.length());

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
			br.close();

		} catch (IOException ioe) {
		}
		return colReturn;
	}

	@SuppressWarnings({ "unused" })
	private static Collection<String> darEspecialidadesMedico(String nomm, String codtem)
			throws FileNotFoundException, IOException {
		Collection<String> colEspecialidades = new ArrayList<String>();
		int sw;
		String codp;
		String codme;
		String enfp;

		FileReader fr = new FileReader(new File(ARCHIVO_SITUACION_PACIENTE));
		BufferedReader br = new BufferedReader(fr);
		String line;

		while ((line = br.readLine()) != null) {
			int index = line.indexOf(":");
			codp = line.substring(0, index);
			int index2 = line.indexOf("|");
			codme = line.substring(index + 1, index2);
			enfp = line.substring(index2 + 1, line.length());
			if (codtem.equals(codme)) {
				colEspecialidades.add(Encryptor.decode(enfp));
			}

		}
		br.close();
		fr.close();
		return colEspecialidades;
	}

	@SuppressWarnings("unused")
	private static Collection<String> darPacientesAtendidosPorMedico(String nomm, String codtem)
			throws FileNotFoundException, IOException {
		Collection<String> colPacientes = new ArrayList<String>();
		String codp;
		String codpa;
		String nompa;
		String codme;
		String enfp;
		int sw = 1;
		int sw1;

		FileReader fr = new FileReader(new File(ARCHIVO_SITUACION_PACIENTE));
		BufferedReader br = new BufferedReader(fr);
		String line;

		while ((line = br.readLine()) != null) {
			int index = line.indexOf(":");
			codp = line.substring(0, index);
			int index2 = line.indexOf("|");
			codme = line.substring(index + 1, index2);
			enfp = line.substring(index2 + 1, line.length());

			if (codme.equals(codtem)) {

				FileReader fr2 = new FileReader(new File(ARCHIVO_DATOS_PACIENTE));
				BufferedReader br2 = new BufferedReader(fr2);
				String line2;

				while ((line2 = br2.readLine()) != null) {
					int index3 = line2.indexOf(":");
					codpa = line2.substring(0, index3);
					nompa = line2.substring(index3 + 1, line2.length());

					if (codpa.equals(codp)) {
						colPacientes.add(nompa);
					}
				}
				br2.close();
				fr2.close();
			}

		}
		br.close();
		fr.close();

		return colPacientes;

	}

	public static void nuevoMedico(String codmed, String nombre, String especializacion) throws Exception {

		FileWriter fw = new FileWriter(new File(ARCHIVO_DATOS_MEDICO), true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		pw.println(codmed + ":" + nombre + "|" + especializacion);
		pw.close();

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
		 * 
		 */

		FileWriter fw = new FileWriter(new File(ARCHIVO_SITUACION_PACIENTE), true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		pw.println(codpac + ":" + codmed + "|" + Encryptor.encode(diagnostico));
		pw.close();

	}

	public static void nuevoPaciente(String codpac, String nompac) throws Exception {

		FileWriter fw = new FileWriter(new File(ARCHIVO_DATOS_PACIENTE), true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		pw.println(codpac + ":" + nompac);
		pw.close();

	}

}
