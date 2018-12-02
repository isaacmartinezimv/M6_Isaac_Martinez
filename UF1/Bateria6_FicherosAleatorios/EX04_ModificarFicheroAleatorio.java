package Bateria6_FicherosAleatorios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EX04_ModificarFicheroAleatorio {
	public static void main(String[] args) throws IOException {
		try {
			int idEmpleado = Integer.parseInt(args[0]);
			Double salario = Double.parseDouble(args[1]);
			leerFicheroAleatorio();
			comprobarId(idEmpleado, salario);
			leerFicheroAleatorio();
		} catch  (ArrayIndexOutOfBoundsException exception) {		
			System.out.println("Debes introducir los datos (ID, Salario) por parametros.");
		}
	}

	public static void comprobarId(int inputId, Double inputSalario) throws IOException {
		File fichero = new File ("AleatorioEmpleado.dat");
		RandomAccessFile file = new RandomAccessFile (fichero, "r");

		int id, dep, posicion;
		Double salario;
		char apellido[]= new char[10], aux;
		posicion = 0;

		for ( ; ; ) {
			file.seek (posicion); // Nos posicionamos en posicion
			id = file.readInt(); // Obtengo identificar de Empleado
			if (inputId > 0 && id == inputId) {
				for ( int i = 0; i < apellido.length; i++) {
					aux = file.readChar(); // Voy leyendo carácter a carácter el apellido y lo guardo
					apellido[i]=aux; // en el array apellido
				}

				String apellidos = new String (apellido);
				dep = file.readInt(); //Lectura de departamento y salario
				salario = file.readDouble();
				double nuevoSalario = salario + inputSalario;
				modificarSalarioEmpleado(posicion, inputId, apellidos, dep, nuevoSalario);
				break;
			} else {
				posicion += 36;
				if (posicion == file.length()) {
					System.out.printf("El ID no existe. No se pueden actualizar los datos del empleado.\n\n");
					break; // Si he recorrido todo el fichero, salgo del for
				}
			}
		}
		file.close();
	}

	public static void modificarSalarioEmpleado(int writePosicion, int writeId, String oldApellido, int oldDep, Double writeSalario) throws IOException {
		File fichero = new File ("AleatorioEmpleado.dat");
		RandomAccessFile file = new RandomAccessFile (fichero , "rw");

		int id = writeId;
		StringBuffer buffer = null; //Buffer para almacenar apellido
		buffer = new StringBuffer (oldApellido);
		buffer.setLength(10); // Fijo en 10 caracteres la longitud del apellido
		int dep = oldDep;
		Double salario = writeSalario;

		file.seek (writePosicion); // Nos posicionamos en posicion
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
		System.out.println("\n");
		file.close();
	}
}
