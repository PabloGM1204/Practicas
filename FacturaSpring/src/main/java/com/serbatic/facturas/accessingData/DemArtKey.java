package com.serbatic.facturas.accessingData;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class DemArtKey implements Serializable {

    @lombok.Setter
    @lombok.Getter
    @Column(name="id_demand")
    long idDemand;

    @lombok.Setter
    @lombok.Getter
    @Column(name="id_art")
    long idArt;

    public DemArtKey(Long idDemand, Long idArt){
        this.idDemand=idDemand;
        this.idArt=idArt;
    }

    public DemArtKey() {

    }

}
