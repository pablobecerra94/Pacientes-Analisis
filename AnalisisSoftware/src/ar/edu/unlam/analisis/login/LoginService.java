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
 * Login service para el sistema de pacientes. 
 * El usuario sera un String simple
 * La contraseña sera un String simple, pero al momento de guardar se encripta con md5
 * @author fpezzola
 *
 */

public class LoginService {
	
	private static final String ARCHIVO_USUARIOS = "src//resources//datousr.bd";
	/*private static final String ARCHIVO_USUARIOS = "C:\\datousr.txt";*/
	
	/**
	 * Realiza el login. Valida el usuario existente y hashea la contraseña con md5 para pdoer comparar
	 * con el archivo de usuarios.
	 * Si el usuario es veridico y su contraseña tambien, lo mete en el threadlocal para luego pdoer hacer el 
	 * log sin tener que ir pasandolo de capa en capa.
	 * @param usr
	 * @param password
	 * @return
	 * @throws Exception
	 */
	
	public static String login(String usr, String password) throws Exception{
		String errors = "";
		String pswArchivo= "";
		
		pswArchivo = darContraseñaUsuario(usr);
		if(!pswArchivo.isEmpty()){
			String encriptMD5=Encryptor.applyMD5(password); //encriptamos con md5 para comparar con la del archivo
			if(!encriptMD5.equals(pswArchivo)){
				errors = "La contraseña es incorrecta"; //no coincide contraseña
			}else{
				UserProvider.setuUser(usr);
			}
		}else{
			errors = "El usuario no se encuentra registrado";
		}
		
		return errors;
	}
	
	/**
	 * Lo saca de "sesion" es decir lo saca del thread local
	 */
	
	public static void logout(){
		UserProvider.removeUser();
	}
	
	
	/**
	 * Registramos usuarios de la siguiente manera:
	 * usrname
	 * password - encriptada con md5
	 * ---------
	 * usrname
	 * ...
	 */
	public static String signin(String usr, String password) throws Exception{
		String errors = "";
		String pswArchivo= "";
		pswArchivo = darContraseñaUsuario(usr);
		if(pswArchivo.isEmpty()){ //Como la pasw esta vacia, el usuario NO existe
			FileWriter fw = new FileWriter(new File(ARCHIVO_USUARIOS),true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(usr+":"+Encryptor.applyMD5(password));//usr:contraseña
			pw.close();
			
		}else{
			errors = "El usuario ya se encuentra registrado."; //como la pswd NO esta vacia, el usuario existe
		}
		
		return errors;
	}
	
	private static String darContraseñaUsuario(String usr) throws Exception{
		String usrArchivo="",pswArchivo= "";
			try {
				FileReader fr = new FileReader(new File(ARCHIVO_USUARIOS));
				BufferedReader br = new BufferedReader(fr);
				String line;
				
				while ((line = br.readLine())!=null) {
					int index = line.indexOf(":");
					usrArchivo = line.substring(0, index);
					pswArchivo = line.substring(index+1,line.length());


					if (usr.equals(usrArchivo)) // compara a ver si coinicide con el nombre de suaurio del archivo
					{
						return pswArchivo;
					}
				}

			} catch (IOException ioe) {
			}
			
			return "";
	}

}
