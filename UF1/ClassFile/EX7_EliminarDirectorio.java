package ClassFile;
/* EJERCICIO 7
 * 
 *  Realiza un programa que elimine el directorio creado en el punto anterior. Para ello
habrás de eliminar todos los archivos que se encuentren dentro del directorio.

 *  */

import java.io.*;
import java.nio.file.Files;


public class EX7_EliminarDirectorio {
	public static void main(String[] args) {
		File dir = new File(".\\NuevoDir");

		try {

			if (dir.exists()) {
			    for (File file : dir.listFiles()) {
			        file.delete();
			    }
			    dir.delete();
				System.out.println("Ficheros borrados con exito");

			} else {
				System.out.println("No existe el directorio");
			}
			
		} catch (Exception E ) {
			System.out.println("Se ha encontrado un error inesperado en la ejecucion del programa");
		}

	}
}

