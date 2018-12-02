package Bateria7_AccesoDOM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EX02_CrearTxtEmpleados {
	public static void main ( String [] args) throws IOException {
		File fichero = new File ("Empleados.txt");  // declaración fichero
		String [] empleado = {"1:Fernandez:10:1000.45",
				"2:Gil:20:2400.60",
				"3:Lopez:10:3000.10",
				"4:Ramos:10:1520.35",
				"5:Fulano:35:7412.64",
				"6:Mengano:12:1439.36",
				"7:Romero:21:2341.76"};

		if (fichero.exists()) {
			fichero.delete();
		} 
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
		for (int i = 0; i < empleado.length; i++) {
			bw.append(empleado[i]+"\n");
		}
		bw.close();
	}
}


