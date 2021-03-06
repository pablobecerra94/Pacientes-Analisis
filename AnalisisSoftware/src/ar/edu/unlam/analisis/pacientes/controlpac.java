package ar.edu.unlam.analisis.pacientes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ar.edu.unlam.analisis.enums.ETipoAccion;
import ar.edu.unlam.analisis.enums.ETipoInforme;
import ar.edu.unlam.analisis.util.Encryptor;
import ar.edu.unlam.analisis.util.LogUtils;

public class controlpac {

	public static final String ARCHIVO_DATOS_MEDICO = "src//resources//datomed.pac";
	public static final String ARCHIVO_SITUACION_PACIENTE = "src//resources//situpac.txt";
	public static final String ARCHIVO_DATOS_PACIENTE = "src//resources//datopac.txt";

	@SuppressWarnings("unused")
	public static Collection<String> getInformeMedico(String codmed, ETipoInforme tipoInforme) throws Exception {
		Collection<String> colReturn = new ArrayList<String>(); //Coleccion que tendra el informe de especialidades o pacientes
		String codm = "", nomm = "", espm; // Strings auxiliares para codigo,nombre y especializacion del medico
		try {
			FileReader fr = new FileReader(new File(ARCHIVO_DATOS_MEDICO)); //Con estas clases
			BufferedReader br = new BufferedReader(fr);						//puedo leer el archivo
			String line; //Aca voy a leer cada linea del archivo
			while ((line = br.readLine()) != null) { //Leo hasta el final del archivo
				int index = line.indexOf(":");   //Me paro en ":"
				codm = line.substring(0, index); //Obtengo el codigo del medico
				int index2 = line.indexOf("|");  //Me paro en "|"
				nomm = line.substring(index + 1, index2); //Obtengo el nombre del medico
				espm = line.substring(index2 + 1, line.length()); //Obtengo la especializacion del medico

				if (codm.equals(codmed))	 // compara el codigo extraido de la
											// tabla "datomed" con el codigo
					{						// digitado
					if (ETipoInforme.ESPECIALIDADES.equals(tipoInforme)) { //Se pide el informe de especialidades
						colReturn = darEspecialidadesMedico(nomm, codmed); //Cargo a la coleccion con las especialidades
					}
					if (ETipoInforme.PACIENTES.equals(tipoInforme)) {  //Se pide el informe de pacientes
						colReturn = darPacientesAtendidosPorMedico(nomm, codmed); //Cargo a la coleccion con los pacientes
					}
				}
			}
			br.close(); //Cierro el BufferedReader

		} catch (IOException ioe) { //Posible excepcion de no poder leer el archivo
		}
		return colReturn; //Devuelvo el informe de pacientes o especialidades segun corresponda
	}

	@SuppressWarnings({ "unused" })
	private static Collection<String> darEspecialidadesMedico(String nomm, String codtem) throws FileNotFoundException, IOException {
		Collection<String> colEspecialidades = new ArrayList<String>(); //Aca se van a guardar las especialidades del medico
		String codp; //Codigo de Paciente
		String codme; //Codigo de Medico
		String enfp; //Enfermedad Paciente

		FileReader fr = new FileReader(new File(ARCHIVO_SITUACION_PACIENTE)); // Con estas clases se
		BufferedReader br = new BufferedReader(fr);							  // puede leer el archivo	
		String line; //Aca voy a leer cada linea del archivo

		while ((line = br.readLine()) != null) { //Leo hasta el final del archivo
			int index = line.indexOf(":"); //Me paro en ":"
			codp = line.substring(0, index); //Obtengo el codigo del paciente
			int index2 = line.indexOf("|"); //Me paro en "|"
			codme = line.substring(index + 1, index2);	//Obtengo el codigo del medico
			enfp = line.substring(index2 + 1, line.length()); //Obtengo la enfermedad del paciente
			if (codtem.equals(codme)) {
				colEspecialidades.add(Encryptor.decode(enfp)); //Decodifico la enfermedad del paciente
			}

		}
		br.close(); //Cierro el BufferedReader
		fr.close(); //Cierro el FileReader
		return colEspecialidades; //Devuelvo las especialidades del medico
	}

	@SuppressWarnings("unused")
	private static Collection<String> darPacientesAtendidosPorMedico(String nomm, String codtem) throws FileNotFoundException, IOException {
		Collection<String> colPacientes = new ArrayList<String>(); //Aca se van a guardar los pacientes
		String codp;  //Codigo del paciente
		String codpa; //Codigo del paciente
		String nompa; //Nombre del paciente
		String codme; //Codigo del medico
		String enfp;  //Enfermedad del paciente
		

		FileReader fr = new FileReader(new File(ARCHIVO_SITUACION_PACIENTE)); //Con estas clases
		BufferedReader br = new BufferedReader(fr);							  //puedo leer el archivo	
		String line; //Aca voy a leer cada linea del archivo

		while ((line = br.readLine()) != null) { //Leo hasta el final del archivo
			int index = line.indexOf(":");//Me paro en ":"
			codp = line.substring(0, index); //Obtengo el codigo del paciente
			int index2 = line.indexOf("|");//Me paro en "|"
			codme = line.substring(index + 1, index2); //Obtengo el codigo del medico
			enfp = line.substring(index2 + 1, line.length()); //Enfermedad del paciente

			if (codme.equals(codtem)) { //Si coinciden los codigos de medico:

				FileReader fr2 = new FileReader(new File(ARCHIVO_DATOS_PACIENTE)); //Con estas clases
				BufferedReader br2 = new BufferedReader(fr2);						//puedo leer el archivo	
				String line2; //Aca voy a leer cada linea del archivo

				while ((line2 = br2.readLine()) != null) {//Leo hasta el final del archivo
					int index3 = line2.indexOf(":");//Me paro en ":"
					codpa = line2.substring(0, index3); //Obtengo el codigo del paciente
					nompa = line2.substring(index3 + 1, line2.length()); //Obtengo el nombre del paciente

					if (codpa.equals(codp)) { //Si coinciden los codigos de paciente
						colPacientes.add(nompa); //Agrego el nombre del paciente a la coleccion
					}
				}
				br2.close(); //Se cierra el BufferedReader
				fr2.close(); //Se cierra el FileReader
			}

		}
		br.close(); //Se cierra el BufferedReader
		fr.close(); //Se cierra el FileReader

		return colPacientes; //Devuelvo la coleccion

	}

	public static void nuevoMedico(String codmed, String nombre, String especializacion, boolean doLog) throws Exception {

		FileWriter fw = new FileWriter(new File(ARCHIVO_DATOS_MEDICO), true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		pw.println(codmed + ":" + nombre + "|" + especializacion);
		if(doLog){
			LogUtils.log(ARCHIVO_DATOS_MEDICO, ETipoAccion.ALTA_MEDICO,codmed,nombre,especializacion);
		}
		pw.close();

	}

	public static void nuevaSituacionPaciente(String codpac, String codmed, String diagnostico, boolean doLog) throws Exception {
		FileWriter fw = new FileWriter(new File(ARCHIVO_SITUACION_PACIENTE), true); //Con estas
		BufferedWriter bw = new BufferedWriter(fw);									// clases podemos
		PrintWriter pw = new PrintWriter(bw);										// escribir en el archivo
		pw.println(codpac + ":" + codmed + "|" + Encryptor.encode(diagnostico));	//Imprimo la linea
		if(doLog){
			LogUtils.log(ARCHIVO_SITUACION_PACIENTE, ETipoAccion.ALTA_SITUACION_PACIENTE, codpac,codmed,Encryptor.encode(diagnostico));
		}
		pw.close();//Cierro el PrintWriter
	}


	public static void nuevoPaciente(String codpac, String nompac, boolean doLog) throws Exception {

		FileWriter fw = new FileWriter(new File(ARCHIVO_DATOS_PACIENTE), true);//Con estas
		BufferedWriter bw = new BufferedWriter(fw);								// clases podemos
		PrintWriter pw = new PrintWriter(bw);									// escribir en el archivo
		pw.println(codpac + ":" + nompac);// Imprimo en el archivo
		if(doLog){
			LogUtils.log(ARCHIVO_DATOS_PACIENTE, ETipoAccion.ALTA_PACIENTE, codpac,nompac);//Guardo el log
		}
		pw.close();//Cierro el PrintWriter
	}

	public static Map<String, String> getTodosLosMedicos() throws IOException {
		Map<String,String> map = new HashMap<String,String>();
		FileReader fr = new FileReader(new File(ARCHIVO_DATOS_MEDICO)); //Con estas clases
		BufferedReader br = new BufferedReader(fr);							  //puedo leer el archivo	
		String line; //Aca voy a leer cada linea del archivo
		String codmed,nommed;

		while ((line = br.readLine()) != null) { //Leo hasta el final del archivo
			int index = line.indexOf(":");//Me paro en ":"
			codmed = line.substring(0, index); //Obtengo el codigo del medico
			nommed = line.substring(index+1,line.indexOf("|"));//Obtengo el nombre del medico
			map.put(codmed, nommed);
		}
		return map;
	}
	

	public static Map<String, String> getTodosLosPacientes() throws IOException {
		Map<String,String> map = new HashMap<String,String>();
		FileReader fr = new FileReader(new File(ARCHIVO_DATOS_PACIENTE)); //Con estas clases
		BufferedReader br = new BufferedReader(fr);							  //puedo leer el archivo	
		String line; //Aca voy a leer cada linea del archivo
		String codPac,nomPac;

		while ((line = br.readLine()) != null) { //Leo hasta el final del archivo
			int index = line.indexOf(":");//Me paro en ":"
			codPac = line.substring(0, index); //Obtengo el codigo del paciente
			nomPac = line.substring(index+1,line.length());//Obtengo el nombre del paciente
			map.put(codPac, nomPac);
		}
		return map;
	}

	public static Collection<String> getListado(String selected) throws IOException {
		Collection<String> col = new ArrayList<String>();
		String archivo = selected.equals("1")?ARCHIVO_DATOS_MEDICO:ARCHIVO_DATOS_PACIENTE;
		FileReader fr = new FileReader(new File(archivo)); //Con estas clases
		BufferedReader br = new BufferedReader(fr);							  //puedo leer el archivo	
		String line; //Aca voy a leer cada linea del archivo
		String nom;

		while ((line = br.readLine()) != null) { //Leo hasta el final del archivo
			int index = line.indexOf(":");//Me paro en ":"
			int index2;
			if(selected.equalsIgnoreCase("2")){
				index2 = line.length();
			}else{
				index2 = line.indexOf("|");
			}
			nom = line.substring(index+1,index2);//Obtengo el nombre
			col.add(nom);
		}
		return col;
	}
	
	

}
