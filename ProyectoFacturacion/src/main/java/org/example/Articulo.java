package org.example;

public class Articulo {
  private String nombre;
  private String categoria;
  private int stock;
  private double precio;


  public Articulo(String nombre, String categoria, int stockArt, double precio) {
    this.nombre = nombre;
    this.categoria = categoria;
    this.stock = stockArt;
    this.precio = precio;
  }


  @Override
  public String toString() {
    return "articulo [, nombre=" + nombre + ", categoria=" + categoria + ", stock=" + stock
        + ", precio=" + precio + "]";
  }

  // getters//


  public String getNombre() {
    return nombre;
  }


  public String getCategoria() {
    return categoria;
  }


  public int getStock() {
    return stock;
  }


  public double getPrecio() {
    return precio;
  }

  // setters//


  public void setNombre(String nombre) {
    this.nombre = nombre;
  }


  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }


  public void setStock(int stock) {
    this.stock = stock;
  }


  public void setPrecio(double precio) {
    this.precio = precio;
  }

}
