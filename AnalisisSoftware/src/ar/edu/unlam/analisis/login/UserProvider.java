package ar.edu.unlam.analisis.login;

public class UserProvider {
	
	private static final ThreadLocal<String> userLogeado = new ThreadLocal<String>();
	
	public static String getUsuarioLogueado(){
		return userLogeado.get();
	}
	
	public static void removeUser(){
		if(userLogeado.get()!=null){
			userLogeado.remove();
		}
	}
	
	public static void setuUser(String usr){
		userLogeado.set(usr);
	}

}
