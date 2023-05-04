package org.example;

public class Factura {
  private int idFac;
  private String dateFac;
  private double total;
  private int numFac;
  private String dirFac;

  public Factura(int idFac, String dateFac, double total, int numFac, String dirFac) {
    this.idFac = idFac;
    this.dateFac = dateFac;
    this.total = total;
    this.numFac = numFac;
    this.dirFac = dirFac;
  }


  // Getters

  public int getIdFac() {
    return idFac;
  }

  public String getDateFac() {
    return dateFac;
  }

  public double getTotal() {
    return total;
  }

  public int getNumFac() {
    return numFac;
  }

  public String getDirFac() {
    return dirFac;
  }

  // Setters

  public void setIdFac(int idFac) {
    this.idFac = idFac;
  }

  public void setDateFac(String dateFac) {
    this.dateFac = dateFac;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public void setNumFac(int numFac) {
    this.numFac = numFac;
  }


  public void setDirFac(String dirFac) {
    this.dirFac = dirFac;
  }
}
