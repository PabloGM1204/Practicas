package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ProyectoFactura {

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    Scanner scanner = new Scanner(System.in);
    Class.forName("com.mysql.cj.jdbc.Driver");

    // Variable Generales //
    String sentenciaSQL = "";
    int entrada;

    Connection conexion =
        DriverManager.getConnection("jdbc:mysql://localhost:3306/pedidos", "root", "");

    do {
      System.out.print("""
          1. Introducir un cliente
          2. Introducir un artículo
          3. Crear un pedido
          4. Mostrar tablas
          5. Facturar
          6. Salir del programa
          Escribe un número: """);
      entrada = scanner.nextInt();
      System.out.println();

      switch (entrada) {
        case 1:

          System.out.print("Introduce el nombre del cliente: ");
          String nombre = scanner.next();

          System.out.print("Introduce el primer apellido: ");
          String apellido1 = scanner.next();

          System.out.print("Introduce el segundo apellido: ");
          String apellido2 = scanner.next();

          scanner.nextLine();

          System.out.print("Introduce el número de teléfono: ");
          String tel = scanner.next();

          System.out.print("Introduce el email: ");
          String email = scanner.next();

          System.out.print("Introduce el DNI: ");
          String dni = scanner.next();



          Cliente cliente = new Cliente(nombre, apellido1, apellido2, tel, email, dni);


          Statement s = conexion.createStatement();
          sentenciaSQL =
              "INSERT INTO cliente (NomCli,ApeCli,Ape2Cli,TelCli,EmailCli,DNICli) VALUES ('"
                  + cliente.getNombre() + "', '" + cliente.getApellido1() + "', '"
                  + cliente.getApellido2() + "', '" + cliente.getNumTel() + "', '"
                  + cliente.getEmailCli() + "', '" + cliente.getDNI() + "')";


          s.execute(sentenciaSQL);
          break;

        case 2:

          System.out.print("Introduce el nombre del artículo: ");
          String nombreArt = scanner.next();

          System.out.print("Introduce la categoría: ");
          String categoriaArt = scanner.next();

          System.out.print("Introduce el precio del artículo separado con coma: ");
          double precioArt = scanner.nextDouble();

          System.out.print("Introduce el stock del artículo: ");
          int stockArt = scanner.nextInt();

          Articulo articulo = new Articulo(nombreArt, categoriaArt, stockArt, precioArt);

          Statement insertArt = conexion.createStatement();
          sentenciaSQL = "INSERT INTO articulo VALUES ('0" + "', '" + articulo.getNombre()
              + "', '" + articulo.getCategoria() + "', '" + articulo.getPrecio() + "', "
              + articulo.getStock() + ")";

          insertArt.execute(sentenciaSQL);
          break;

        case 3:

          HashMap<Integer, Integer> articulos = new HashMap<Integer, Integer>();

          Mostrar.MostrarClientes();

          System.out.print("Introduzca tu codigo de cliente: ");
          int codcli = scanner.nextInt();

          do {

            System.out.print("""
                1. Insertar articulo
                2. Confirmar pedido
                3. Cancelar
                Escribe un número: """);

            entrada = scanner.nextInt();
            System.out.println();

            switch (entrada) {
              case 1:
                // Mostrar todos los articulos//
                Mostrar.MostrarArticulos();

                System.out.print("Inserte el codigo del articulo deseado: ");
                int codart = scanner.nextInt();

                /* Checkear input y que el código existe */
                System.out.print("Inserte la cantidad deseada de su articulo: ");
                int canart = scanner.nextInt();
                if (Check.CheckStock(codart, canart)) {
                  /* Checkear cantidad válida */
                  if (articulos.containsKey(codart)) {
                    articulos.put(codart, canart);
                    System.out.println(
                        "El producto seleccionado ya existia en su pedido, cantidad actualizada. ");
                  } else {
                    articulos.put(codart, canart);
                    System.out.println("Producto añadido correctamente. ");
                  }
                } else {
                  System.out.println("Lo siento, stock insuficiente.");
                }


                break;

              case 2:

                int CodPeMax = 0;
                Statement j = conexion.createStatement();
                sentenciaSQL = "INSERT INTO pedido (CodCli) VALUES ( " + codcli + ")";
                j.execute(sentenciaSQL); // Insertamos el pedido actual en la tabla pedidos//

                sentenciaSQL = "SELECT MAX(CodPe) AS MaxCodPe FROM pedido"; // Verificar que esto
                // funcione//

                ResultSet querySet = j.executeQuery(sentenciaSQL);
                // //Select que devuelve el id del
                // ultimo pedido//

                if (querySet.next()) { // Verificar que esto funcione// //Intentamos guardar

                  CodPeMax = querySet.getInt("MaxCodPe");
                }
                // el valor de la consulta anteior como int//
                for (Map.Entry<Integer, Integer> set : articulos.entrySet()) {
                  sentenciaSQL = "INSERT INTO art_ped (CodPe, CodArt, CantArt) VALUES ( "
                      + CodPeMax + ", " + set.getKey() + ", " + set.getValue() + " )"; // Recorremos
                  // el HashMap y
                  // creamos un
                  // insert con
                  // el codart y
                  // la cant del
                  // mismo por
                  // cada uno //
                  j.execute(sentenciaSQL); // Ejecutamos el isnert anterior de la tabla art_ped
                }
                entrada = 3;
                System.out.println("Se ha creado su pedido correctamente.");
                break;
            }
          } while (entrada != 3);
          break;
        case 4:

          System.out.print(
              "Introduce el nombre de la tabla que quieras listar (Articulo, Cliente, Pedido, Factura): ");
          String eleccion = scanner.next().toLowerCase();

          if (eleccion.equals("articulo")) {
            Mostrar.MostrarArticulos();
          } else if (eleccion.equals("cliente")) {
            Mostrar.MostrarClientes();
          } else if (eleccion.equals("pedido")) {
            Mostrar.MostrarPedido();
          } else if (eleccion.equals("factura")) {
            Mostrar.MostrarFactura();
          }

          break;
        case 5:
          /* MIRAR MAÑANA */
          /* Muestro los pedidos */
          Mostrar.MostrarPedido();
          System.out.println("Introduce el código del pedido que quieras facturar: ");
          String CodPedScanner = scanner.next();
          Statement h = conexion.createStatement();

          /* Consulta para ver que la tabla este vacia */
          sentenciaSQL= "SELECT COUNT(*) AS Cant_filas FROM factura;";
          int exists = 0;
          ResultSet alreadyExists = h.executeQuery(sentenciaSQL);
          if (alreadyExists.next()) {
            exists = alreadyExists.getInt("Cant_filas");
            if (exists > 0) {
              /* Verifico si el pedido ya está facturado */
              sentenciaSQL = "SELECT IF(EXISTS(SELECT * FROM factura WHERE CodPed="
                  + CodPedScanner + "),1,0) AS factura_existe";
              alreadyExists = h.executeQuery(sentenciaSQL);
              if (alreadyExists.next()) {
                exists = alreadyExists.getInt("factura_existe");
                if (exists == 1) {
                  System.out.println("Lo siento el pedido que inenta facturar ya está facturado");
                } else {
                  /*
                   * Coge la factura con numero mas alto, nos quedamos con las 3 ultimas letras las
                   * parseamos a entero y le sumamos uno, luego la parseamos a String y la
                   * concatenamos y hacemos el insert
                   */
                  /* Para sacar el año */
                  Calendar ca = Calendar.getInstance();

                  String anio = Integer.toString(ca.get(Calendar.YEAR));
                  sentenciaSQL = "SELECT MAX(NumFac) FROM factura";
                  ResultSet numeroFacMax = h.executeQuery(sentenciaSQL);
                  String numMaxFac = "";
                  String numFacNuevo = anio;

                  if (numeroFacMax.next()) {
                    numMaxFac = numeroFacMax.getString("MAX(NumFac)");
                  }

                  int ult = Integer.parseInt(numMaxFac.substring(5, 7)) + 1;
                  for (int i = 0; i < 3 - (Integer.toString(ult)).length(); i++) {
                    numFacNuevo += "0";
                  }

                  numFacNuevo += Integer.toString(ult);

                  sentenciaSQL = "INSERT INTO factura (CodPed,NumFac) VALUES ('"
                      + CodPedScanner + "','" + numFacNuevo + "')";
                  h.execute(sentenciaSQL);
                }
              }
            } else {
              /* Si la tabala está vacia mete una factura con numero 2023001 */
              sentenciaSQL =
                  "INSERT INTO factura (NumFac,CodPed) VALUES ('2023001'," + CodPedScanner + ")";
              h.execute(sentenciaSQL);
            }
          }

        default:
          break;
      }
    } while (entrada != 6);
    conexion.close();
  }
}
