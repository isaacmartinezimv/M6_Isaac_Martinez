package bateria1_JDBC;

import java.sql.*;

public class Ejercicio1 {
  public static void main(String[] args) {
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conexion=DriverManager.getConnection 
          ("jdbc:mysql://localhost/ejemplo","austria","austria");
      Statement sentencia =conexion.createStatement();
      String sql = "SELECT * from emple where dept_no = 10";
      ResultSet result = sentencia.executeQuery(sql);
      while (result.next()){
        System.out.printf("%s, %s, %d, %n",
            //result.getInt(1),
            result.getString(2),
            result.getString(3),
            result.getInt(6));
      }
      result.close();
      sentencia.close();
      conexion.close();
    } catch (ClassNotFoundException cn) { cn.printStackTrace();
    } catch (SQLException e) {e.printStackTrace();
    }
  }
}