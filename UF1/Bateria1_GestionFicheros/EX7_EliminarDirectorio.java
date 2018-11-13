package Bateria1_GestionFicheros;
/* EJERCICIO 7
 * 
 *  Realiza un programa que elimine el directorio creado en el punto anterior. Para ello
habr�s de eliminar todos los archivos que se encuentren dentro del directorio.

 *  */

import java.io.*;


public class EX7_EliminarDirectorio {
	public static void main(String[] args) {
		File dir = new File(".\\NuevoDir");

		try {

			if (dir.exists()) {
			    for (File file : dir.listFiles()) {
			        file.delete();
			    }
			    dir.delete();
				System.out.println("Los ficheros han sido borrados con exito");

			} else {
				System.out.println("El directorio seleccionado no existe");
			}
			
		} catch (Exception E ) {
			System.out.println("Error salvaje aparecio, algo funciono de manera inesperada.");
		}

	}
}

