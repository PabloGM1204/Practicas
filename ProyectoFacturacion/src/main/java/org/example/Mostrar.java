package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Mostrar {
  public static Connection getConexion() throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");

    Connection conexion =
        DriverManager.getConnection("jdbc:mysql://localhost:3306/pedidos", "root", "");

    return conexion;
  }

  public static void MostrarArticulos() throws ClassNotFoundException, SQLException {

    Connection conexionBD = Mostrar.getConexion();
    Statement s = conexionBD.createStatement();
    ResultSet listado = s.executeQuery("SELECT * FROM articulo");
    ResultSetMetaData metadata = listado.getMetaData();
    int numColumnas = metadata.getColumnCount();

    for (int i = 1; i <= numColumnas; i++) {
      System.out.print(metadata.getColumnName(i) + "\t");
    }

    System.out.println();

    while (listado.next()) {
      for (int i = 1; i <= numColumnas; i++) {
        System.out.print(listado.getString(i) + "\t");
      }
      System.out.println("");
    }

    System.out.println("");
    conexionBD.close();
  }

  public static void MostrarClientes() throws ClassNotFoundException, SQLException {

    Connection conexionBD = Mostrar.getConexion();
    Statement p = conexionBD.createStatement();
    ResultSet listado = p.executeQuery("SELECT * FROM cliente");
    ResultSetMetaData metadata = listado.getMetaData();
    int numColumnas = metadata.getColumnCount();


    while (listado.next()) {
      for (int i = 1; i <= numColumnas; i++) {
        System.out.print(listado.getString(i));
        System.out.print("   ");
      }
      System.out.println("");
    }
    System.out.println("");
    conexionBD.close();
  }

  public static void MostrarPedido() throws ClassNotFoundException, SQLException {

    Connection conexionBD = Mostrar.getConexion();
    Statement p = conexionBD.createStatement();
    ResultSet listado = p.executeQuery("SELECT * FROM pedido");
    ResultSetMetaData metadata = listado.getMetaData();
    int numColumnas = metadata.getColumnCount();

    while (listado.next()) {
      for (int i = 1; i <= numColumnas; i++) {
        System.out.print(listado.getString(i));
        System.out.print("   ");
      }
      System.out.println("");
    }

    System.out.println("");

    System.out.println("Introduce el número del pedido que quieras ver: ");
    Scanner scanner = new Scanner(System.in);
    String numPedido = scanner.next();

    /* Controlar input del usuario */
    String consulta =
        "SELECT ar.CodArt,NomArt,Categoria,CantArt,Precio,ROUND(Precio*CantArt , 2) AS Subtotal FROM (pedido pe INNER JOIN art_ped arpe ON (pe.CodPe=arpe.CodPe))INNER JOIN articulo ar ON (arpe.CodArt=ar.CodArt) WHERE (pe.CodPe="
            + Integer.parseInt(numPedido) + ")";

    listado = p.executeQuery(consulta);
    metadata = listado.getMetaData();
    numColumnas = metadata.getColumnCount();

    while (listado.next()) {
      for (int i = 1; i <= numColumnas; i++) {
        System.out.print(listado.getString(i));
        System.out.print("   ");
      }
      System.out.println("");
    }

    System.out.println("");
    conexionBD.close();
  }

  public static void MostrarFactura() throws ClassNotFoundException, SQLException {

    Connection conexionBD = Mostrar.getConexion();
    Statement p = conexionBD.createStatement();
    ResultSet listado = p.executeQuery("SELECT * FROM factura");
    ResultSetMetaData metadata = listado.getMetaData();
    int numColumnas = metadata.getColumnCount();

    while (listado.next()) {
      for (int i = 1; i <= numColumnas; i++) {
        System.out.print(listado.getString(i));
        System.out.print("   ");
      }
      System.out.println("");
    }

    System.out.println("");
    conexionBD.close();
  }
}

