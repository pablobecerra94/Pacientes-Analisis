package ar.edu.unlam.analisis.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.apache.commons.codec.digest.DigestUtils;

public class Encryptor {
	
	//como md5 es one-way no hay manera de "desencriptar" una vez encriptado. Solo para passwords.
	public static String applyMD5(String text){
		String encriptMD5=DigestUtils.md5Hex(text);
		return encriptMD5;
	}
	
	//encriptamos la info
	public static String encode(String text) throws UnsupportedEncodingException {
	 	String asB64;
		try {
			asB64 = Base64.getEncoder().encodeToString(text.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException(HandleResponseUtil.COMMON_ERROR);
		}
	 	return asB64;
    }

	//decodificamos la info
    public static String decode(String text) throws UnsupportedEncodingException {
    	byte[] asBytes = Base64.getDecoder().decode(text);
    	return new String(asBytes, "utf-8"); 

    }
	
	

}
