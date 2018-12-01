package Bateria3_Streams.Streams_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class EX2_EscribirFicheroBufferedWriter {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Introduce la ruta donde quieres guardar el archivo: ");
		String ruta = reader.readLine();
		
		System.out.print("Introduce el nombre del archivo que quieres crear: ");
		String fileName = reader.readLine();
		
		String extension = ".txt";
		String dir = ruta+fileName+extension;

		try {
			BufferedWriter fichero = new BufferedWriter (new FileWriter (dir));
			String linea = "Esta es la linea de texto numero: ";
			
			for (int i = 0; i < 10; i++) {
				fichero.write(linea + (i+1));
				fichero.newLine();
			}
			fichero.close();
			System.out.println("El archivo "+ fileName+extension + " se ha creado con �xito");
		}

		catch (FileNotFoundException fn) {
			System.out.println ("No se encuentra el fichero"); 
		}

		catch (IOException io) {
			System.out.println ("Error de E/S"); 
		}
	}
}
