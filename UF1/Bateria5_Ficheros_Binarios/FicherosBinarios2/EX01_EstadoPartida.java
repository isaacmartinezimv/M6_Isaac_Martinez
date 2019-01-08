package Bateria5_Ficheros_Binarios.FicherosBinarios2;

/* Ejercicio 1. Diseña una clase que llamarás EstadoPartida para gestionar el estado de una partida de 3 en raya. 
 * Debe incluir: posición de las piezas (será una matriz 3x3) y a quién le toca tirar (jugador1 o jugador2). */

import java.io.*;

public class EX01_EstadoPartida implements Serializable {
	private int[][] positions = new int [3][3];
	private int currentPlayer;
	
	public EX01_EstadoPartida(int[][] positions, int curPlayer) {
		super();
		this.positions = positions;
		this.currentPlayer = curPlayer;
	}

	public int[][] getPosiciones() {
		return positions;
	}

	public void setPosiciones(int[][] positions) {
		this.positions = positions;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int curPlayer) {
		this.currentPlayer = curPlayer;
	}		
}

