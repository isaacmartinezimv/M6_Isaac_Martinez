package Bateria5_Ficheros_Binarios.FicherosBinarios2;

/* Ejercicio 2. Diseña una clase que llamarás GuardarYRecuperarEstado que disponga de dos métodos: guardarEstado
 * que permitirá guardar en un archivo el estado de la partida y otro, recuperarEstado, que permitirá recuperar el
 * estado de una partida desde archivo. */

import java.io.*;

public class EX02_GuardarYRecuperarEstado {
	public static File nuevoFichero = new File ("EstadoPartida.dat");
	
	public static void guardarPartida(EX01_EstadoPartida partida) throws IOException {
		FileOutputStream archivoSalida = new FileOutputStream (nuevoFichero);
		ObjectOutputStream datosSalida = new ObjectOutputStream (archivoSalida);
		
		datosSalida.writeObject (partida);
		datosSalida.close();
	}
	
	public static EX01_EstadoPartida cargarPartida() throws IOException, ClassNotFoundException {
		FileInputStream archivoEntrada = new FileInputStream (nuevoFichero);
		ObjectInputStream datosEntrada = new ObjectInputStream (archivoEntrada);
		
		EX01_EstadoPartida partida = (EX01_EstadoPartida) datosEntrada.readObject();
		datosEntrada.close();
		return partida;
	}
}
