package bateria5_BDOO.I;

/* Ejercicio 1:
 * 1. Crea la clase Paises con dos atributos y sus getter y setter. Los atributos son: private int id; private String nombrepais;
 * 2. Añade también el método toString() para que devuelva el nombre del país: public String toString() {return nombrepais;}
 * 3. Crea la clase Jugadores (como el ejemplo anterior) y añade el siguiente atributo con sus getter y setter: private Paises pais;
 * 4. Crea una clase Java (con el método main()) que cree una base de datos de nombre EQUIPOS.DB e inserte países y los jugadores de esos países.
 * 5. Añade otra clase Java para visualizar los países y los jugadores que hay en la base de datos. */

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import bateria5_BDOO.I.Jugadores;
import bateria5_BDOO.I.Paises;

public class EX01_Ejercicio01 {
	public static void main(String[] args) {

		Paises pais1 = new Paises(1,"Reino Unido");
		Paises pais2 = new Paises(2,"Alemania");
		Paises pais3 = new Paises(3,"España");
		Paises pais4 = new Paises(4,"Francia");

		Jugadores jugador1 = new Jugadores("Kevin Richardson", "Furbol", "Newcastle upon Tyn", 22, pais1);
		Jugadores jugador2 = new Jugadores("Dennis Schröder", "Basket", "Braunschweig", 23, pais2);
		Jugadores jugador3 = new Jugadores("Rafael Nadal", "Tenis", "Manacor", 24, pais3);
		Jugadores jugador4 = new Jugadores("Jean van de Valde", "Golf", "Paris", 25, pais4);

		ODB odb = ODBFactory.open("EQUPOS.DB");     // Abrir 
		odb.store(pais1);
		odb.store(pais2);
		odb.store(pais3);
		odb.store(pais4);
		odb.store(jugador1);               // Almacenamos 
		odb.store(jugador2);
		odb.store(jugador3);
		odb.store(jugador4);

		Objects<Paises> objectsPaises = odb.getObjects(Paises.class);
		Objects<Jugadores> objectsJugadores = odb.getObjects(Jugadores.class);    //recuperamos todos los objetos
		System.out.printf("%d Paises: %n", objectsPaises.size());
		System.out.printf("%d Jugadores: %n", objectsJugadores.size());

		int i = 1;
		while(objectsJugadores.hasNext()) {                               // visualizar los objetos     
			Jugadores jug = objectsJugadores.next();
			System.out.printf("%d: %s %n", i++, jug.getPais());  
		} 

		System.out.print("");
		while(objectsPaises.hasNext()) {                               // visualizar los objetos     
			Paises pai = objectsPaises.next();
			System.out.printf("%d: %s %s %n", i++, pai.getId(), pai.getNombrepais());  
		} 
		odb.close(); // Cerrar BD      
	}
}