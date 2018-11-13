package Bateria2_Streams.Streams_1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EX2_ModificarFicheroTexto {
	public static void main ( String [] args) throws IOException {
		File fichero = new File ("./././UF1/Bateria2_Streams/Streams_1/EX1_LeerFicheroTexto.java");  // declaración fichero
		FileReader flu = new FileReader (fichero); // creamos flujo de entrada hacia el fichero
		
		char[] buf = new char[20];
		
		while ((flu.read(buf)) != -1) {    //Vamos leyendo carácter a carácter
			System.out.println (buf); //hacemos cast a char del entero leído
		}
		flu.close();
	}
}
