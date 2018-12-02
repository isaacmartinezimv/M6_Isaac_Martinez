package Bateria6_FicherosAleatorios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EX01_EscrituraLecturaFicheroAleatorio {
	public static void main(String[] args) throws IOException {
		escribirFicheroAleatorio();
		leerFicheroAleatorio();
	}

	public static void escribirFicheroAleatorio() throws IOException {
		File fichero = new File ("AleatorioEmpleado.dat");
		RandomAccessFile file = new RandomAccessFile (fichero , "rw");
		
		String apellido[] = {"Fernández", "Gil", "López", "Ramos"};
		int dep[] =  {10,20,10,10};
		Double salario[] = {1000.45,2400.60,3000.0,1500.56};
		StringBuffer buffer = null; //Buffer para almacenar apellido
		int n = apellido.length; //Número de elementos en el array
		
		for (int i = 0; i<n; i++) {   //Recorro los arrays
			file.writeInt (i+1);
			buffer = new StringBuffer (apellido[i]);
			buffer.setLength(10); // Fijo en 10 caracteres la longitud del apellido
			file.writeChars (buffer.toString());
			file.writeInt(dep[i]);
			file.writeDouble (salario[i]);
		}
		file.close();  // No olvidarse de cerrar el fichero
	}	

	public static void leerFicheroAleatorio() throws IOException {
		File fichero = new File ("AleatorioEmpleado.dat");
		RandomAccessFile file = new RandomAccessFile (fichero, "r");

		int id, dep ,posicion;
		Double salario;
		char apellido[]= new char[10], aux;
		posicion = 0;

		for ( ; ; ){
			file.seek (posicion); // Nos posicionamos en posicion
			id = file.readInt(); // Obtengo identificar de Empleado
			for ( int i =0; i<apellido.length; i++) {
				aux = file.readChar(); // Voy leyendo carácter a carácter el apellido y lo guardo
				apellido[i]=aux; // en el array apellido
			}

			String apellidos = new String (apellido);
			dep = file.readInt(); //Lectura de departamento y salario
			salario = file.readDouble();

			if (id > 0)
				System.out.printf("ID: %s, Apellido: %s, Departamento: %d, Salario: %.2f %n", id, apellidos.trim(), dep, salario);
			posicion = posicion + 36;

			if (file.getFilePointer() ==file.length()) break; // Si he recorrido todo el fichero, salgo del for
		}                  
		file.close();
	}
}
