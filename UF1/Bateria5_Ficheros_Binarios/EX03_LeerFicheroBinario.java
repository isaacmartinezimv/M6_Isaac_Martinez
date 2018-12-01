package Bateria5_Ficheros_Binarios;
/* EJERCICIO 3. Ahora escribe un programa que permita visualizar los datos grabados anteriormente en el
 * fichero FicheroDatos.dat. Se deben obtener en el mismo orden en el que se escribieron, es decir, primero
 * obtenemos el nombre y luego la edad. */

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class EX03_LeerFicheroBinario {
	public static void main (String [] args) throws IOException	{

		File fichero = new File ("FicheroDatos.dat");
		DataInputStream filein = new DataInputStream (new FileInputStream(fichero));
		try {
			while (true) {
				System.out.print(filein.readUTF());
				System.out.println(filein.readInt());
			}
		} catch (EOFException eo) {
			System.out.println ("Fin de Lectura");
		}
		filein.close();
	}
}
