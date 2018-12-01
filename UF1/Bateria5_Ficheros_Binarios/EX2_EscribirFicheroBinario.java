package Bateria5_Ficheros_Binarios;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EX2_EscribirFicheroBinario {
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

