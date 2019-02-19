package bateria2_JDBC2.I;

/* 3. Genera un programa que devuelva la clave primaria de la tabla departamentos y la
 * clave ajena que apunta a la tabla departamentos. NOTA: revisa que estén creadas 
 * las claves; tanto la primaria como la foránea. */

import java.sql.*;

public class EX03_Ejercicio03 {
	public static void main (String [] args) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection
					("jdbc:mysql://localhost/ejemplo","austria","austria");
			
			DatabaseMetaData dbmd = conexion.getMetaData();
			ResultSet result = null;
			ResultSet result2 = null;

			result = dbmd.getPrimaryKeys("ejemplo", null,"depart");
			result2 = dbmd.getExportedKeys("ejemplo", null, "emple");

			while (result.next()){
				String key = result.getString("COLUMN_NAME");
				System.out.printf("- Key: %s%n", key);  
			}

			while (result2.next()) {
				String foreign = result2.getString("FKCOLUMN_NAME");
				System.out.printf("- Foreign key: %s%n", foreign);
			}
			conexion.close();
		}
		catch (ClassNotFoundException cn) {cn.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}
}
