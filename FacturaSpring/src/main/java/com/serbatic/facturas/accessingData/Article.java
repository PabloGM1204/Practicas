package com.serbatic.facturas.accessingData;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Article {

  @lombok.Setter
  @lombok.Getter
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idArt;

  @lombok.Setter
  @lombok.Getter
  @Column(length = 50)
  @Size(min = 1, max = 50)
  private String name;

  @lombok.Setter
  @lombok.Getter
  @Column(length = 50)
  @Size(min = 1, max = 50)
  private String category;

  @lombok.Setter
  @lombok.Getter
  private int stock;
  
  @lombok.Setter
  @lombok.Getter
  private double price;

}
