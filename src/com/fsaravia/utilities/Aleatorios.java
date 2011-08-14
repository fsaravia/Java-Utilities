package com.fsaravia.utilities;




public class Aleatorios {
    
	public static String generarCadenaAleatoria(int tamanio){
		char[] caracteres = new char[tamanio];
		for (int i=0;i<caracteres.length;i++){
			caracteres[i]=Aleatorios.generarCaracterAleatorio();
		}
		return new String(caracteres);
	}
	
	public static char generarCaracterAleatorio(){
		char[] caracteres = {'1','2','3','4','5','6','7','8','9','0','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		java.util.Random r = new java.util.Random();
		return caracteres[r.nextInt(caracteres.length)];
	}
	
	public static String generarPassword(){
		return Aleatorios.generarCadenaAleatoria(6);
	}

}
