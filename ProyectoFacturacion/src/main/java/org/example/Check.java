package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Check {

  public static boolean CheckStock(int cod, int can) throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");

    Connection conexion =
        DriverManager.getConnection("jdbc:mysql://localhost:3306/pedidos", "root", "");

    String consulta = "SELECT Stock from articulo WHERE CodArt=" + cod;

    Statement s = conexion.createStatement();
    ResultSet listado = s.executeQuery(consulta);
    int stockArticulo = 0;

    if (listado.next()) {
      stockArticulo = listado.getInt("Stock");
    }

    if (stockArticulo - can < 0) {
      return false;
    } else {
      String consulta2 = "UPDATE articulo SET Stock=Stock-" + can + " WHERE CodArt=" + cod;
      s.execute(consulta2);
      return true;
    }
  }

}
