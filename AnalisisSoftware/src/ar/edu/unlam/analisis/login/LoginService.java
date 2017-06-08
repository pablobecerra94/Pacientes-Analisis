package ar.edu.unlam.analisis.login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ar.edu.unlam.analisis.util.Encryptor;

/**
 * Login service para el sistema de pacientes. El usuario sera un String simple
 * La contraseÃ±a sera un String simple, pero al momento de guardar se encripta
 * con md5
 * 
 * @author fpezzola
 *
 */

public class LoginService {

	private static final String ARCHIVO_USUARIOS = "src//resources//datousr.bd";
	/* private static final String ARCHIVO_USUARIOS = "C:\\datousr.txt"; */

	/**
	 * Realiza el login. Valida el usuario existente y hashea la contraseÃ±a con
	 * md5 para pdoer comparar con el archivo de usuarios. Si el usuario es
	 * veridico y su contraseÃ±a tambien, lo mete en el threadlocal para luego
	 * pdoer hacer el log sin tener que ir pasandolo de capa en capa.
	 * 
	 * @param usr
	 * @param password
	 * @return
	 * @throws Exception
	 */

	public static String login(String usr, String password) throws Exception { 
		String errors = ""; //String auxiliar para indicar errores
		String pswArchivo = ""; //contraseña del archivo
		pswArchivo = darContraseñaUsuario(usr); //traigo la contraseña del usuario 
		if (!pswArchivo.isEmpty()) {//si existe un usuario:
			String encriptMD5 = Encryptor.applyMD5(password); // encriptamos con md5 
			if (!encriptMD5.equals(pswArchivo)) //comparo la pass con la del archivo
				errors = "La contraseÃ±a es incorrecta"; // no coincide la contraseña			 
			 else 
				UserProvider.setuUser(usr); //coincide la contraseña
		} else 
			errors = "El usuario no se encuentra registrado"; // no existe el usuario
		return errors;// si todo esta OK devolvera un string vacio
	} 

	/**
	 * Lo saca de "sesion" es decir lo saca del thread local
	 */

	public static void logout() {
		UserProvider.removeUser(); //Lo saca de "sesion" es decir lo saca del thread local
	}

	/**
	 * Registramos usuarios de la siguiente manera: usrname password -
	 * encriptada con md5 --------- usrname ...
	 */
	public static String signin(String usr, String password) throws Exception {
		String errors = "";//String auxiliar para indicar errores
		String pswArchivo = ""; //contraseña del archivo
		pswArchivo = darContraseñaUsuario(usr);//traigo la contraseña del usuario 
		if (pswArchivo.isEmpty()) { // Como la pasw esta vacia, el usuario NO existe					 
			FileWriter fw = new FileWriter(new File(ARCHIVO_USUARIOS), true);    //gracias a estas
			BufferedWriter bw = new BufferedWriter(fw);							// clases podemos 
			PrintWriter pw = new PrintWriter(bw);								// imprimir en el archivo
			pw.println(usr + ":" + Encryptor.applyMD5(password));// usr:contraseÃ±a
			pw.close(); //Cerramos el printWriter ya que sino el archivo no se escribe

		} else 
			errors = "El usuario ya se encuentra registrado."; // como la pswd
																// NO esta
																// vacia, el
																// usuario
																// existe
		return errors; // si todo esta OK devolvera un string vacio
	}

	private static String darContraseñaUsuario(String usr) throws Exception {
		String usrArchivo = "", pswArchivo = ""; //String auxiliares para el usuario y contraseña
		try {
			FileReader fr = new FileReader(new File(ARCHIVO_USUARIOS)); // Con estas clases puedo
			BufferedReader br = new BufferedReader(fr); 				// leer el archivo de usuarios
			String line; //String para poder leer linea a linea el archivo

			while ((line = br.readLine()) != null) { //para leer hasta el final del archivo
				int index = line.indexOf(":"); // Me posiciono en la parte de la linea con ":"
				usrArchivo = line.substring(0, index); //Obtengo el usuario	del archivo
				pswArchivo = line.substring(index + 1, line.length()); //Obtengo la contraseña del archivo
				if (usr.equals(usrArchivo)) // compara a ver si coincide con el
				{							// nombre de usuario del archivo
					br.close(); //Cierro el BufferedReader
					return pswArchivo; //Devuelvo la contraseña
				}
			}
			br.close();//Cierro el BufferedReader
		} catch (IOException ioe) { //Posible excepcion de no poder leer el archivo
		}

		return ""; // El archivo esta vacio o no se encontro la contraseña
	}

}
