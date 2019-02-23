package bateria2_JDBC2.I;

/* 4. Busca información sobre la interfaz ResultSetMetaData y realiza un programa 
 * utilizando dicha interfaz que obtenga el número de columnas y el tipo de columnas
 * devueltos por la consulta “SELECT * FROM DEPARTAMENTOS”. */

import java.sql.*;

public class EX04_Ejercicio04 {

	public static void main (String [] args) {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo","austria","austria");
			DatabaseMetaData dbmd = connexion.getMetaData();
			String sql = "SELECT * from depart";

			Statement statement = connexion.createStatement();
			ResultSet result = statement.executeQuery(sql);
			ResultSetMetaData rsmd =  result.getMetaData();  
			int columns = rsmd.getColumnCount();
			result = dbmd.getColumns(null,"ejemplo","depart",null);

			System.out.printf("Table has %d columns\n",columns);

			while (result.next()){
				String typeName = result.getString(6);
				System.out.printf("type: %s %n", typeName);
			}
			connexion.close();
		}
		catch (ClassNotFoundException cn) {cn.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}
}
