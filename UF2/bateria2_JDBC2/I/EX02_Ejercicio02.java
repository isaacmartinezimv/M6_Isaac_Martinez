package bateria2_JDBC2.I;

import java.sql.*;

/* 2. Genera un programa en Java que muestre el nombre, el tipo, el tamaño y si puede ser
 * nulo o no, de las columnas de la tabla departamentos. */

public class EX02_Ejercicio02 {
	public static void main (String [] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo","austria","austria");
			DatabaseMetaData dbmd = conexion.getMetaData();
			ResultSet result = null;

			result = dbmd.getColumns(null,"ejemplo","depart",null);

			while (result.next()){
				String nombre = result.getString(4);
				String tipoDatos = result.getString(6);
				String dimensiones = result.getString(7);
				String tipoNull = result.getString(11);

				if (tipoNull == "1") {
					tipoNull = "Yes";
				} else {
					tipoNull = "No";
				}
				System.out.printf("- Nombre: %s, Tipo Datos: %s, Dimension: %s, Puede ser null: %s %n", nombre, tipoDatos, dimensiones, tipoNull);
			}
			conexion.close();
		}
		catch (ClassNotFoundException cn) {cn.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}
}