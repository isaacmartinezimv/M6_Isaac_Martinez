package Bateria5_Ficheros_Binarios.FicherosBinarios2;

/* Ejercicio 3. Crea un programa en java que genere un objeto EstadoPartida, modifique la  posición de algunas fichas del tablero, 
 * guarde en un archivo el estado de la partida, recupere el estado desde el archivo y lo muestre por pantalla. */

import java.io.*;

public class EX03_UsaGuardaYRecuperaEstado {
	public static void main (String[] args) throws IOException, ClassNotFoundException {
		// Iniciamos la partida con unos datos
		int[][] EstadoPartida = {{0,2,0},{0,1,0},{2,1,0}};
		EX01_EstadoPartida partida1 = new EX01_EstadoPartida (EstadoPartida, 1);
		
		System.out.println("Composicion Inicial del tablero: \n");
		System.out.println(" -------------");
		
		for (int i = 0; i < 3; i++) {
				System.out.print(" | " + partida1.getPosiciones()[i][0]);
				System.out.print(" | " + partida1.getPosiciones()[i][1]);
				System.out.print(" | " + partida1.getPosiciones()[i][2] + " |\n");
				System.out.println(" -------------");
		}
		
		System.out.println("\nEl jugador activo es el " + partida1.getCurrentPlayer());
		System.out.println("\n------------------------------------------------------\n");
		
		
		// Cambiamos la configuracion de la partida
		int[][] NuevoEstadoPartida = {{1,2,0},{2,1,0},{2,1,0}};
		partida1.setCurrentPlayer(2);
		partida1.setPosiciones(NuevoEstadoPartida);
		
		// Guardamos partida
		EX02_GuardarYRecuperarEstado.guardarPartida(partida1);	
		
		// Cargamos partida
		EX01_EstadoPartida partidaFinal = (EX01_EstadoPartida) EX02_GuardarYRecuperarEstado.cargarPartida();
		
		System.out.println("Composicion FINAL del tablero: \n");
		System.out.println(" -------------");
		
		for (int i = 0; i < 3; i++) {
				System.out.print(" | " + partidaFinal.getPosiciones()[i][0]);
				System.out.print(" | " + partidaFinal.getPosiciones()[i][1]);
				System.out.print(" | " + partidaFinal.getPosiciones()[i][2] + " |\n");
				System.out.println(" -------------");
		}
		System.out.println("\nEl jugador activo es el " + partidaFinal.getCurrentPlayer());
	}
}
