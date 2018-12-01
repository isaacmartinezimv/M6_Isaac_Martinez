package Bateria3_Streams.Streams_2;

import java.io.*;

public class EX3_EscribirFicherotexto {
	public static void main (String [] args) throws IOException {
		File fichero = new File("FicheroTexto.txt");
		FileWriter fic = new FileWriter (fichero);
		String prov[] = {"Albacete", "Avila", "Badajoz", "Caceres", "Huelva", "Jaen", "Madrid",
				"Segovia", "Soria", "Toledo", "Valladolid", "Zamora"};    

		for (int i = 0; i < prov.length-1; i++) {
			fic.write(prov[i] + ", "); // Escribimos ciudad a ciudad
		}

		fic.append(prov[prov.length-1]); //Añadimos la ultima ciudad (sin coma al final)
		fic.close ();   // cerramos fichero
	}
}
