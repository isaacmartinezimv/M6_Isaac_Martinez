package Bateria3_Streams.Streams_2;
/* EJERCICIO 2. Modifica el ejemplo anterior para, en vez de escribir los caractere
 * uno a uno, escribir todo el array usando el método write (char [] buf) */

import java.io.*;

public class EX02_EscribirFicheroTexto {
	  
	public static void main (String [] args) throws IOException {
	    File fichero = new File("FicheroTexto.txt");
	    FileWriter fic = new FileWriter (fichero);
	    String cadena = " Esto es una prueba con FileWriter";
	    char [] cad = cadena.toCharArray();
	    
	    fic.write(cad);
	    fic.append ('*'); // añadimos un asterisco al final
	    fic.close ();   // cerramos fichero
	  }
	}
