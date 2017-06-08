package ar.edu.unlam.analisis.login;

public class UserProvider {
	
	private static final ThreadLocal<String> userLogeado = new ThreadLocal<String>();
	
	public static String getUsuarioLogueado(){
		return userLogeado.get(); //Tomo al usuario logeado
	}
	
	public static void removeUser(){
		if(userLogeado.get()!=null) //Si el usuario esta logeado
			userLogeado.remove(); //Remuevo al usuario
	}
	
	public static void setuUser(String usr){
		userLogeado.set(usr); //Seteo al usuario como logeado
	}

}
