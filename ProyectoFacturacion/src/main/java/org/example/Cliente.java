package org.example;

public class Cliente {
  private String nombre;
  private String apellido1;
  private String apellido2;
  private String DNI;
  private String emailCli;
  private String numTel;


  public Cliente(String nombre, String ape1, String ape2, String num, String em, String DNI) {
    this.nombre = nombre;
    this.apellido1 = ape1;
    this.apellido2 = ape2;
    this.numTel = num;
    this.emailCli = em;
    this.DNI = DNI;
  }

  public String getApellido1() {
    return this.apellido1;
  }


  public String getApellido2() {
    return this.apellido2;
  }


  public String getEmailCli() {
    return this.emailCli;
  }


  public String getNombre() {
    return this.nombre;
  }


  public String getDNI() {
    return this.DNI;
  }

  public String getNumTel() {
    return this.numTel;
  }

  public void setApellido1(String apellido1) {
    this.apellido1 = apellido1;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setEmailCli(String emailCli) {
    this.emailCli = emailCli;
  }

  public void setNumTel(String numTel) {
    this.numTel = numTel;
  }

  public void setDNI(String DNI) {
    this.DNI = DNI;
  }

  public void setApellido2(String apellido2) {
    this.apellido2 = apellido2;
  }

}

