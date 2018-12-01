package Bateria3_Streams.Streams_1;
// EJERCICIO 1. Prueba el código anterior

import java.io.*;

public class EX1_LeerFicheroTexto {
	public static void main ( String [] args) throws IOException {
		File fichero = new File ("./././UF1/Bateria2_Streams/Streams_1/EX1_LeerFicheroTexto.java");  // declaración fichero
		FileReader flu = new FileReader (fichero); // creamos flujo de entrada hacia el fichero

		int i;
		while ((i=flu.read())!=-1)     //Vamos leyendo carácter a carácter
			System.out.println ((char) i); //hacemos cast a char del entero leído

		flu.close();
	}
}