package org.example;

import java.util.ArrayList;

public class Pedido {
  private int idPed;
  private boolean facturado;
  private String datePed;
  private ArrayList<Articulo> articulos;
  private int cantidad;
  private double total;

  public Pedido(int idPed, boolean facturado, String datePed, ArrayList<Articulo> articulos,
      int cantidad, double total) {
    this.idPed = idPed;
    this.facturado = facturado;
    this.datePed = datePed;
    this.articulos = articulos;
    this.cantidad = cantidad;
    this.total = total;
  }

  // Getters//

  public int getIdPed() {
    return idPed;
  }

  public boolean isFacturado() {
    return facturado;
  }

  public String getDatePed() {
    return datePed;
  }

  public ArrayList<Articulo> getArticulos() {
    return articulos;
  }

  public double getTotal() {
    return total;
  }

  public int getCantidad() {
    return cantidad;
  }

  // Setters

  public void setIdPed(int idPed) {
    this.idPed = idPed;
  }

  public void setFacturado(boolean facturado) {
    this.facturado = facturado;
  }

  public void setDatePed(String datePed) {
    this.datePed = datePed;
  }

  public void setArticulos(ArrayList<Articulo> articulos) {
    this.articulos = articulos;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }
}
