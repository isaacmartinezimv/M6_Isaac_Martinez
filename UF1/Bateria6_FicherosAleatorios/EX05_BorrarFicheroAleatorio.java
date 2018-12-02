package Bateria6_FicherosAleatorios;
/* EJERCICIO 5. Crea un programa Java que al ejecutarlo desde la línea de comandos reciba un 
 * identificador de empleado y lo borre. Se hará un borrado lógico marcando el registro con la 
 * siguiente información: el identificador será igual a -1, el apellido será igual al identificador
 * que se borra, y el departamento y salario serán 0.*/

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EX05_BorrarFicheroAleatorio {
	public static void main(String[] args) throws IOException {
		try {
			int idEmpleado = Integer.parseInt(args[0]);
			leerFicheroAleatorio();
			comprobarId(idEmpleado);
			leerFicheroAleatorio();	
		} catch  (ArrayIndexOutOfBoundsException exception) {		
			System.out.println("Debes introducir un ID valido por parametro.");
		}
	}

	public static void comprobarId(int inputId) throws IOException {
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
				modificarSalarioEmpleado(posicion, inputId, apellidos, dep, salario);
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

		oldApellido = Integer.toString(writeId);
		writeId = -1;
		
		StringBuffer buffer = null; //Buffer para almacenar apellido
		buffer = new StringBuffer (oldApellido);
		buffer.setLength(10); // Fijo en 10 caracteres la longitud del apellido
		oldDep = 0;
		writeSalario = 0.0;

		file.seek (writePosicion); // Nos posicionamos en posicion
		file.writeInt (writeId);
		file.writeChars (buffer.toString());
		file.writeInt(oldDep);
		file.writeDouble (writeSalario);
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

			//if (id >0)
				System.out.printf("ID: %s, Apellido: %s, Departamento: %d, Salario: %.2f %n", id, apellidos.trim(), dep, salario);
			posicion = posicion + 36;

			if (file.getFilePointer() ==file.length()) break; // Si he recorrido todo el fichero, salgo del for
		}
		System.out.println("\n");
		file.close();
	}
}
