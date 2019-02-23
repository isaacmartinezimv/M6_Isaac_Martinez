package bateria2_JDBC2.II;

/* 2. Realiza un programa que cree una vista (de nombre “totales”) que contenga por 
 * cada departamento el número de departamento, el nombre, el número de empleados que
 * tiene y el salario medio. */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EX02_Ejercicio02 {
	
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo","austria","austria");			
			
			Statement statement = connexion.createStatement();
			String instruccionSQL = String.format("create view totales as (select e.dept_no, d.dnombre,"
					+ "count(e.emp_no) 'num_empleados',avg(e.salario) from emple e inner join depart "
					+ "d on e.dept_no=d.dept_no group by e.dept_no)");
			String instruccionSQL2 = String.format("select * from totales");
			statement.executeUpdate(instruccionSQL);
			ResultSet result = statement.executeQuery(instruccionSQL2);

			while (result.next()) {
				System.out.printf("%s, %s, %s, %s %n", result.getString(1), result.getString(2), result.getString(3), result.getString(4));
			}
			statement.close();
			connexion.close();
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
