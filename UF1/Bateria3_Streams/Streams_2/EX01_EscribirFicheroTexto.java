package Bateria3_Streams.Streams_2;
// EJERCICIO 1. Copia el ejemplo anterior y pruébalo

import java.io.*;
public class EX01_EscribirFicheroTexto {
	public static void main (String [] args) throws IOException {
		File fichero = new File("FicheroTexto.txt");
		FileWriter fic = new FileWriter (fichero);
		String cadena = " Esto es una prueba con FileWriter";
		char [] cad = cadena.toCharArray () ;
		for ( int i=0; i < cad.length ; i++)
			fic.write (cad[i]); // se va escribiendo carácter a carácter
		fic.append ('*'); // añadimos un asterisco al final
		fic.close ();   // cerramos fichero
	}
}