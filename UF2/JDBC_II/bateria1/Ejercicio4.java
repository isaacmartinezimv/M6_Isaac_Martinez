package JDBC_II.bateria1;

// https://examples.javacodegeeks.com/core-java/sql/resultsetmetadata/java-sql-resultsetmetadata-example/
// https://docs.oracle.com/javase/7/docs/api/java/sql/ResultSetMetaData.html

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio4 {
	public static void main (String [] args) {
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection
					("jdbc:mysql://localhost/ejemplo","austria","austria");
			DatabaseMetaData dbmd = conexion.getMetaData();
			String sql = "SELECT * from depart";
			
			Statement sentencia = conexion.createStatement();
			ResultSet result = sentencia.executeQuery(sql);
            java.sql.ResultSetMetaData rsmd =  result.getMetaData();
            
            int columns = rsmd.getColumnCount();
			result = dbmd.getColumns(null,"ejemplo","depart",null);

            System.out.printf("La tabla tiene %d columnas\n",columns);
			while (result.next()){
				String typeName = result.getString(6);
				System.out.printf("type: %s, %n", typeName);
			}

			conexion.close();
		}
		catch (ClassNotFoundException cn) {cn.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}
}