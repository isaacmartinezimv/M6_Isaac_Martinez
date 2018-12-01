package Bateria3_Streams.Streams_3;
// EJERCICIO 3. Repite el ejercicio anterior pero ahora utilizando la clase PrintWriter

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EX03_EscribirFicheroPrintWriter {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Introduce la ruta donde quieres guardar el archivo: ");
		String ruta = reader.readLine();
		
		System.out.print("Introduce el nombre del archivo que quieres crear: ");
		String fileName = reader.readLine();
		
		String extension = ".txt";
		String dir = ruta+fileName+extension;

		try {
			PrintWriter fichero = new PrintWriter (new File(dir));
			String linea = "Esta es la linea de texto numero: ";
			
			for (int i = 0; i < 10; i++) {
				fichero.write(linea + (i+1));
				fichero.println();
			}
		
			fichero.close();
			System.out.println("El archivo "+ fileName+extension + " se ha creado con �xito");
		}

		catch (IOException io) {
			System.out.println ("Error de E/S"); 
		}
	}
}

