package Bateria2_Streams.Streams_2;
import java.io.*;

public class EX2_EscribirFicheroTexto {
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
