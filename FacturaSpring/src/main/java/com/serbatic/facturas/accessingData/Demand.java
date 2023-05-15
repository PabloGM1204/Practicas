package com.serbatic.facturas.accessingData;

import jakarta.persistence.*;

import java.util.Date;


@Entity
public class Demand {
  @lombok.Setter
  @lombok.Getter
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idDemand;

  @lombok.Setter
  @lombok.Getter
  @Temporal(TemporalType.DATE)
  private Date date;

  @lombok.Setter
  @lombok.Getter
  private boolean invoiced=false;

  @lombok.Setter
  @lombok.Getter
  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "user_id")
  private User user;


}
