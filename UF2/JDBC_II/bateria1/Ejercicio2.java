package JDBC_II.bateria1;

import java.sql.*;
public class Ejercicio2 {
	public static void main (String [] args) {
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection
					("jdbc:mysql://localhost/ejemplo","austria","austria");
			DatabaseMetaData dbmd = conexion.getMetaData();
			ResultSet result = null;

			result = dbmd.getColumns(null,"ejemplo","depart",null);
			while (result.next()){
				String name = result.getString(4);
				String typeName = result.getString(6);
				String size = result.getString(7);
				String nullType = result.getString(11);
				System.out.printf("name: %s, typeName: %s, size: %s, nullAceptedBolean: %s %n",name, typeName, size, nullType);
			}
			conexion.close();
		}
		catch (ClassNotFoundException cn) {cn.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}
}