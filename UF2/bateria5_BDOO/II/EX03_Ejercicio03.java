package bateria5_BDOO.II;

import bateria5_BDOO.I.*;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

/* Ejercicio 3.  Realiza un programa que añada un año a la edad de todos los jugadores. */

public class EX03_Ejercicio03 {
	public static void main(String[] args) {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		Objects<Jugadores> objects = odb.getObjects(Jugadores.class);

		while(objects.hasNext()){  
			Jugadores jugador = objects.next();
			jugador.setEdad(jugador.getEdad() + 1);
			odb.store(jugador);
		}
		odb.commit();
		odb.close(); 
	}
}

