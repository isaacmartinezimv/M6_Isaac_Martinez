package bateria2_JDBC2.I;

import java.sql.*;

// 1. Prueba el programa del ejemplo pero contra tu MV.

public class EX01_Ejercicio01 {
	public static void main (String [] args) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo","austria","austria");
			DatabaseMetaData dbmd = conexion.getMetaData();
			ResultSet result = null;

			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url = dbmd.getURL();
			String usuario = dbmd.getUserName();
			
			System.out.printf("Nombre: %s %n", nombre);
			System.out.printf("Nombre: %s %n", driver);
			System.out.printf("Nombre: %s %n", url);
			System.out.printf("Nombre: %s %n", usuario);

			result = dbmd.getTables(null,"ejemplo",null,null);

			while (result.next()){
				String catalogo = result.getString(1);
				String esquema = result.getString(2);
				String tabla = result.getString(3);
				String tipo = result.getString(4);
				System.out.printf("%s - Catalogo: %s, Esquema: %s, Nombre: %s %n", tipo, catalogo, esquema, tabla);
			}
			conexion.close();
		}
		catch (ClassNotFoundException cn) {cn.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}
}

