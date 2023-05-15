package com.serbatic.facturas.accessingData;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Invoice {

    @lombok.Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idInvoice;

    @lombok.Setter
    @lombok.Getter
    @Temporal(TemporalType.DATE)
    private Date date;

    @lombok.Setter
    @lombok.Getter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="idDemand")
    private Demand demand;

}
