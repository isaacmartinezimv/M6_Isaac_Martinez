package Bateria6_FicherosAleatorios;
/* EJERCICIO 3. INSERCION. Crea un programa Java que inserte datos en el fichero aleatorio. 
 * El programa ejecutará desde la línea de comandos y debe recibir 4 parámetros: identificador de
 * empleado, apellido, departamento y salario. Antes de insertar se comprobará si el identificador 
 * existe, en ese caso se debe visualizar un mensaje indicándolo; si no existe se deberá insertar*/

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EX03_InsertarEnFicheroAleatorio {
	public static void main(String[] args) throws IOException {
		try {
			int idEmpleado = Integer.parseInt(args[0]);
			String apellido = args[1];
			int departamento = Integer.parseInt(args[2]);
			Double salario = Double.parseDouble(args[3]);

			comprobarId(idEmpleado, apellido, departamento, salario);
			leerFicheroAleatorio();
		} catch  (ArrayIndexOutOfBoundsException exception) {		
			System.out.println("Introduce (ID, Apellido, Departamento y Salario) por parametros.");
		}
	}

	public static void comprobarId(int inputId, String inputApellido, int inputDpt, Double inputSalario) throws IOException {
		File fichero = new File ("AleatorioEmpleado.dat");
		RandomAccessFile file = new RandomAccessFile (fichero, "r");

		int id, dep, posicion;
		Double salario;
		char apellido[]= new char[10], aux;
		posicion = 0;

		for ( ; ; ) {
			file.seek (posicion); // Nos posicionamos en posicion
			id = file.readInt(); // Obtengo identificar de Empleado
			if (id == inputId) {
				for ( int i = 0; i < apellido.length; i++) {
					aux = file.readChar(); // Voy leyendo carácter a carácter el apellido y lo guardo
					apellido[i]=aux; // en el array apellido
				}

				String apellidos = new String (apellido);
				dep = file.readInt(); //Lectura de departamento y salario
				salario = file.readDouble();

				System.out.printf("El ID ya existe. No se puede introducir otro empleado con este ID.\n\n");
				break;
			} else {
				posicion += 36;
				if (posicion == file.length()) {
						if (inputId > 0) {
							escribirFicheroAleatorio(inputId, inputApellido, inputDpt, inputSalario);
						} else {
							System.out.println("El ID no es valido. \n\n");
						}
						break; // Si he recorrido todo el fichero, salgo del for
					}
				}
			}
		file.close();
	}

	public static void escribirFicheroAleatorio(int writeId, String writeApellido, int writeDpt, Double writeSalario) throws IOException {
		File fichero = new File ("AleatorioEmpleado.dat");
		RandomAccessFile file = new RandomAccessFile (fichero , "rw");

		int id = writeId;
		StringBuffer buffer = null; //Buffer para almacenar apellido
		buffer = new StringBuffer (writeApellido);
		buffer.setLength(10); // Fijo en 10 caracteres la longitud del apellido
		int dep = writeDpt;
		Double salario = writeSalario;
		int posicion = (int) file.length();
		file.seek (posicion); // Nos posicionamos en posicion
		file.writeInt (id);
		file.writeChars (buffer.toString());
		file.writeInt(dep);
		file.writeDouble (salario);
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

			if (id >0)
				System.out.printf("ID: %s, Apellido: %s, Departamento: %d, Salario: %.2f %n", id, apellidos.trim(), dep, salario);
			posicion = posicion + 36;

			if (file.getFilePointer() ==file.length()) break; // Si he recorrido todo el fichero, salgo del for
		}                  
		file.close();
	}
}

