package ar.edu.unlam.analisis.util;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileUtils {

	public static DataInputStream leerArchivo(String archivo) throws FileNotFoundException {
		DataInputStream data = null;
		data = new DataInputStream(new FileInputStream(archivo));
		return data;
	}
}
