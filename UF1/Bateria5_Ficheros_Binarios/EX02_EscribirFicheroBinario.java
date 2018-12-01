package Bateria5_Ficheros_Binarios;
/* EJERCICIO 2. Escribe un programa que inserte datos en "FicherosDatos.dat". Los datos los tomará de dos
 * arrays definidos en el propio programa. Uno contendrá los nombres de una serie de personas y el otro sus
 * edades. Se irá recorriendo los arrays e iremos escribiendo en el fichero el nombre (mediante el método
 * writeUTF(String str) y la edad (writeInt (int v)). NOTA: si queremos añadir bytes al final del fichero 
 * (FicheroDatos.dat) se puede usar el siguiente constructor: FileOutputStream fileout = new FileOutputStream
 * (fichero, true) */

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EX02_EscribirFicheroBinario {
	public static void main (String [] args) throws IOException	{
		try {
			File fichero = new File ("FicheroDatos.dat");

			String [] nombres = new String[] {"Addrianne", "Olfrid", "Vilkas", "Lydia", "Farkas"};
			int [] edades = new int[] {10,25,31,56,42};

			DataOutputStream fileout = new DataOutputStream (new FileOutputStream(fichero));

			for (int i=0; i<5; i++) {
				fileout.writeUTF(nombres[i]+" ");
				fileout.writeInt(edades[i]);
			}
			fileout.close();
			System.out.println("El archivo se ha creado con �xito");
		}
		
		catch (IOException io) {
			System.out.println ("Error de E/S"); 
		}
	}
}

