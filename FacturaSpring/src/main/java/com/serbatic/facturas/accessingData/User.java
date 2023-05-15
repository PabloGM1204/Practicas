package com.serbatic.facturas.accessingData;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class User {
  @Getter
  @Setter
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Setter
  @Getter
  @Column(length = 25)
  @Size(min = 1, max = 25)
  private String name;

  @Getter
  @Setter
  @Column(length = 25)
  @Size(min = 1, max = 25)
  private String firstSurname;

  @Setter
  @Getter
  @Column(length = 25)
  @Size(min = 1, max = 25)
  private String secondSurname;

  @Setter
  @Getter
  @Column(length = 106)
  @Size(min = 1, max = 106)
  private String email;

  @Temporal(TemporalType.DATE)
  private @Getter @Setter Date dischargeDate;
}
